/*************************************************************************
  copyright   : Copyright (C) 2014, chenbichao, All rights reserved.
              : www.peergine.com, www.pptun.com
              :
  filename    : pgLibLive.java
  discription : 
  modify      : create, chenbichao, 2014/07/24

*************************************************************************/

package com.peergine.android.live;

import android.content.Context;
import android.hardware.Camera.CameraInfo;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceView;

import com.peergine.plugin.lib.pgLibJNINode;
import com.peergine.plugin.lib.pgLibJNINodeProc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class pgLibLive {
	public static final int PG_ERR_Normal = 0;
	public static final int PG_ERR_System = 1;
	public static final int PG_ERR_BadParam = 2;
	public static final int PG_ERR_BadClass = 3;
	public static final int PG_ERR_BadMethod = 4;
	public static final int PG_ERR_BadObject = 5;
	public static final int PG_ERR_BadStatus = 6;
	public static final int PG_ERR_BadFile = 7;
	public static final int PG_ERR_BadUser = 8;
	public static final int PG_ERR_BadPass = 9;
	public static final int PG_ERR_NoLogin = 10;
	public static final int PG_ERR_Network = 11;
	public static final int PG_ERR_Timeout = 12;
	public static final int PG_ERR_Reject = 13;
	public static final int PG_ERR_Busy = 14;
	public static final int PG_ERR_Opened = 15;
	public static final int PG_ERR_Closed = 16;
	public static final int PG_ERR_Exist = 17;
	public static final int PG_ERR_NoExist = 18;
	public static final int PG_ERR_NoSpace = 19;
	public static final int PG_ERR_BadType = 20;
	public static final int PG_ERR_CheckErr = 21;
	public static final int PG_ERR_BadServer = 22;
	public static final int PG_ERR_BadDomain = 23;
	public static final int PG_ERR_NoData = 24;
	public static final int PG_ERR_Unknown = 0xff;


	///-----------------------------------------------------
	// Event listen interface.
	public interface OnEventListener {
		void event(String sAct, String sData, String sRender);
	}


	///------------------------------------------------------
	// Api methods.
	
	// Get PG Node object.
	public pgLibJNINode GetNode() {
		return m_Node;
	}

	// Call to SetEventListener() setting the event listen object.
	public void SetEventListener(OnEventListener eventListener) {
		m_eventListener = eventListener;
	}

	public boolean Initialize(int iMode, String sUser, String sPass,
		String sSvrAddr, String sRelayAddr, int iP2PTryTime, String sVideoParam, Context oCtx)
	{
		try {
			// Check Mode parameters.
			if (iMode != pgLibLiveMode.Capture
				&& iMode != pgLibLiveMode.Render)
			{
				OutString("pgLibLive.Initialize: invalid iMode.");
				return false;
			}

			if (sUser.equals("")) {
				OutString("pgLibLive.Initialize: User is null");
				return false;
			}
			
			if (m_bInited) {
				return true;
			}

			// Init JNI lib.
			if (!NodeLibInit(oCtx)) {
				OutString("pgLibLive.Initialize: Peergine plugin invalid.");
				return false;
			}
			
			// Check video parameters.
			boolean bCheckParam = true;
			pgLibJNINode NodeTemp = new pgLibJNINode();
			if (iMode == pgLibLiveMode.Capture) {
				String sCode = NodeTemp.omlGetContent(sVideoParam, "Code");
				String sMode = NodeTemp.omlGetContent(sVideoParam, "Mode");
				int iVideoCode = ParseInt(sCode, -1);
				int iVideoMode = ParseInt(sMode, -1);
				if ((iVideoCode < 1 || iVideoCode > VIDEO_CODE_Butt)
					|| (iVideoMode < 0 || iVideoMode > VIDEO_MODE_Butt))
				{
					OutString("pgLibLive.Initialize: Invalid code=" + sCode + ", mode=" + sMode);
					bCheckParam = false;
				}
			}
			if (!bCheckParam) {
				NodeLibClean();
				return false;
			}

			// Set init status.
			m_bInited = true;

			// Create Timer message handler.
			if (!TimerInit()) {
				Clean();
				return false;
			}

			// Create Node objects.
			m_Node = new pgLibJNINode();
			m_NodeProc = new pgLibLiveNodeProc();

			// Init status
			m_sObjSvr = "";
			m_sObjSelf = "";
			m_sSvrAddr = "";
			m_bEventEnable = false;
			m_bStarted = false;
			m_bLogin = false;
			m_bCapPeerCheckTimer = false;
			m_sLanScanRes = "";
	
			// Store parameters.
			m_iMode = iMode;
			m_sUser = sUser;
			m_sPass = sPass;
			m_sInitSvrName = "pgConnectSvr";
			m_sInitSvrAddr = sSvrAddr;
			m_sRelayAddr = sRelayAddr;
			m_sVideoParam = sVideoParam;
			m_iP2PTryTime = iP2PTryTime;
			m_sObjCap = "";
			m_sCapAddr = "";
			m_sAudioParam = "";
	
			String sCameraNo = m_Node.omlGetContent(m_sVideoParam, "CameraNo");
			if (sCameraNo.equals("")) {
				m_sVideoParam += "(CameraNo){" + CameraInfo.CAMERA_FACING_FRONT + "}";
			}

			if (m_iMode == pgLibLiveMode.Capture) {
				m_sObjSelf = "_CAP_" + m_sUser;
				m_sDevID = m_sUser;
				m_sGroupName = "thisGroup_" + m_sDevID;
			}
			else if (m_iMode == pgLibLiveMode.Render) {
				int iTemp = m_Random.nextInt();
				if (iTemp < 0) {
					iTemp = -iTemp;
				}
				m_sObjSelf = "_RND_" + m_sUser + '_' + Integer.valueOf(iTemp).toString();
			}
	
			if (!NodeStart()) {
				OutString("pgLibLive.Initialize: Node start failed.");
				Clean();
				return false;
			}
			
	
			return true;
		}
		catch (Exception ex) {
			OutString("pgLibLive.Initialize: ex=" + ex.toString());
			Clean();
			return false;
		}
	}

	public void Clean() {
		try {
			TimerClean();

			NodeStop();

			m_sObjSvr = "";
			m_sObjSelf = "";

			if (m_Node != null){
				m_Node.Stop();
				m_Node = null;
			}
			m_NodeProc = null;

			if (m_bInited) {
				NodeLibClean();
				m_bInited = false;
			}
		}
		catch (Exception ex) {
			OutString("pgLibLive.Clean: ex=" + ex.toString());
		}
	}
	
	public void EventEnable(boolean bEnable) {
		m_bEventEnable = bEnable;
	}

	public String GetSelfPeer() {
		return m_sObjSelf;
	}

	// Create view for node.
	public SurfaceView WndCreate(int iX, int iY, int iW, int iH) {
		if (m_Node != null) {
			return (SurfaceView)m_Node.WndNew(iX, iY, iW, iH);
		}
		return null;
	}
	public void WndDestroy() {
		if (m_Node != null) {
			m_Node.WndDelete();
		}
	}

	// Start and stop video capture and render.
	public boolean Start(String sCapID) {
		if (m_bApiStart) {
			return true;
		}

		if (m_iMode == pgLibLiveMode.Render) {
			m_sObjCap = "_CAP_" + sCapID;
			m_sDevID = sCapID;
			m_sGroupName = "thisGroup_" + m_sDevID;
		}

		if (!ServiceStart()) {
			return false;
		}

		m_bApiStart = true;
		return true;
	}

	public void Stop() {
		if (m_Node == null) {
			return;
		}

		m_bApiStart = false;
		ServiceStop();

		if (m_iMode == pgLibLiveMode.Render) {
			m_Node.ObjectDelete("File_" + m_sObjSelf);
			m_sObjCap = "";
		}

		m_iFileFlag = 0;
	}
	
	// Video start and stop
	public boolean VideoStart() {
		if (!m_bStarted) {
			return false;
		}

		if (m_bApiVideoStart) {
			return true;
		}
		
		if (!VideoInit(true)) {
			return false;
		}

		m_bApiVideoStart = true;
		return true;
	}

	public void VideoStop() {
		if (!m_bStarted) {
			return;
		}

		if (m_bApiVideoStart) {
			VideoClean(true);
			m_bApiVideoStart = false;
		}
	}

	// Audio start and stop
	public boolean AudioStart() {
		if (!m_bStarted) {
			return false;
		}

		if (m_bApiAudioStart) {
			return true;
		}

		if (!AudioInit()) {
			return false;
		}

		m_bApiAudioStart = true;
		return true;
	}

	public void AudioStop() {
		if (!m_bStarted) {
			return;
		}

		if (m_bApiAudioStart) {
			AudioClean();
			m_bApiAudioStart = false;
		}
	}

	// Send notify at capture side.
	public boolean NotifySend(String sMsg) {
		if (m_Node == null) {
			OutString("pgLibLive.NotifySend: Node object is null!");
			return false;
		}

		if (!m_bStarted) {
			OutString("pgLibLive.NotifySend: Not start!");
			return false;
		}

		if (m_iMode == pgLibLiveMode.Capture) {
			int iErr = m_Node.ObjectRequest("thisData_" + m_sDevID, 32, sMsg,
					"pgLibLive.NotifySend");
			if (iErr > 0) {
				OutString("pgLibLive.NotifySend: iErr=" + iErr);
				return false;
			}
			return true;
		}
		else {
			return false;
		}
	}

	// Send message at capture side or render side
	public boolean MessageSend(String sData, String sRender) {
		if (m_Node == null) {
			OutString("pgLibLive.MessageSend: Node object is null!");
			return false;
		}

		if (!m_bStarted) {
			OutString("pgLibLive.MessageSend: Not start!");
			return false;
		}

		String sMsg = "Msg?" + sData;
		if (m_iMode == pgLibLiveMode.Capture) {
			int iErr = m_Node.ObjectRequest(sRender, 36, sMsg, "pgLibLive.MessageSend");
			if (iErr > 0) {
				OutString("pgLibLive.MessageSend: iErr=" + iErr);
				return false;
			}
			return true;
		}
		else {
			int iErr = m_Node.ObjectRequest(m_sObjCap, 36, sMsg, "pgLibLive.MessageSend");
			if (iErr > 0) {
				if (iErr == 5) {
					CapPeerCheck();
				}
				OutString("pgLibLive.MessageSend: iErr=" + iErr);
				return false;
			}
			return true;
		}
	}

	// Pull one MJPEG frame.
	public boolean FramePull() {
		if (m_Node == null) {
			OutString("pgLibLive.FramePull: Node object is null!");
			return false;
		}

		if (!m_bStarted) {
			OutString("pgLibLive.FramePull: Not start!");
			return false;
		}

		if (m_iMode == pgLibLiveMode.Render) {
			int iErr = m_Node.ObjectRequest(m_sObjCap, 36, "FrmPull?", "pgLibLive.FramePull");
			if (iErr > 0) {
				if (iErr == 5) {
					CapPeerCheck();
				}
				OutString("pgLibLive.FramePull: iErr=" + iErr);
				return false;
			}
			return true;
		}
		else {
			return false;
		}
	}

	// Scan the captures in the same lan.
	public boolean LanScanStart() {
		if (m_Node == null) {
			OutString("pgLibLive.LanScanStart: Node object is null!");
			return false;
		}

		if (m_iMode != pgLibLiveMode.Render) {
			OutString("pgLibLive.LanScanStart: Can only scan at render side!");
			return false;
		}

		if (m_bApiLanScan) {
			return true;
		}

		int iErr = m_Node.ObjectRequest(m_sObjSvr, 42, "(Timeout){5}", "pgLibLive.LanScan");
		if (iErr > 0) {
			OutString("pgLibLive.LanScanStart: iErr=" + iErr);
			return false;
		}

		m_bApiLanScan = true;
		return true;
	}
	
	public boolean VideoSource(int iCameraNo) {
		if (m_iMode != pgLibLiveMode.Capture) {
			return false;
		}
		if (!m_bApiVideoStart) {
			OutString("pgLibLive.VideoSource: Video has not started!");
			return false;
		}

		if (!CameraSwitch(iCameraNo)) {
			OutString("pgLibLive.VideoSource: Switch camera failed!");
			return false;
		}

		m_sVideoParam = m_Node.omlDeleteEle(m_sVideoParam, "CameraNo", 1, 0);
		m_sVideoParam += "(CameraNo){" + iCameraNo + "}";

		return true;
	}

	public boolean VideoCamera(String sJpgPath) {
		if (m_Node == null) {
			OutString("pgLibLive.VideoCamera: Node object is null!");
			return false;
		}

		if (!m_bApiVideoStart) {
			OutString("pgLibLive.VideoCamera: Video has not started!");
			return false;
		}

		String sPathTemp = sJpgPath;
		if (sPathTemp.lastIndexOf(".jpg") < 0
			&& sPathTemp.lastIndexOf(".JPG") < 0)
		{
			sPathTemp += ".jpg";
		}

		String sData = "(Path){" + m_Node.omlEncode(sPathTemp) + "}";
		int iErr = m_Node.ObjectRequest(("thisLive_" + m_sDevID), 37, sData, "pgLibLive.VideoCamera");
		if (iErr > 0) {
			OutString("pgLibLive.VideoCamera: iErr=" + iErr);
			return false;
		}

		return true;
	}
	
	public boolean VideoModeSize(int iMode, int iWidth, int iHeight) {
		if (m_Node == null) {
			OutString("pgLibLive.VideoModeSize: Not initialize");						
			return false;
		}

		return VideoRedefModeSize(iMode, iWidth, iHeight);
	}

	public boolean VideoShowMode(int iMode) {
		if (m_Node == null) {
			OutString("pgLibLive.VideoShowMode: Not initialize");						
			return false;
		}

		if (m_iMode != pgLibLiveMode.Render) {
			return false;
		}

		if (!m_Node.ObjectAdd("_vTemp", "PG_CLASS_Video", "", 0)) {
			OutString("pgLibLive.VideoShowMode: Add object failed.");						
			return false;
		}

		m_Node.ObjectRequest("_vTemp", 2, "(Item){10}(Value){" + iMode + "}", "");
		m_Node.ObjectDelete("_vTemp");

		return true;
	}
	
	public boolean VideoParam(String sParam) {
		
		if (m_Node == null) {
			OutString("pgLibLive.VideoParam: Not initialize");						
			return false;
		}

		if (m_iMode != pgLibLiveMode.Capture) {
			return false;
		}

		OutString("pgLibLive.VideoParam: sParam=" + sParam);						
		
		int iCode = ParseInt(m_Node.omlGetContent(sParam, "Code"), 0);
		int iMode = ParseInt(m_Node.omlGetContent(sParam, "Mode"), 0);
		int iRate = ParseInt(m_Node.omlGetContent(sParam, "Rate"), 0);
		int iCameraNo = CameraInfo.CAMERA_FACING_FRONT;
		String sCameraNo = m_Node.omlGetContent(sParam, "CameraNo");
		if (!sCameraNo.equals("")) {
			iCameraNo = ParseInt(sCameraNo, CameraInfo.CAMERA_FACING_FRONT);
		}
		int iBitRate = ParseInt(m_Node.omlGetContent(sParam, "BitRate"), 0);
		int iMaxStream = ParseInt(m_Node.omlGetContent(sParam, "MaxStream"), 0);

		int iOldCode = ParseInt(m_Node.omlGetContent(m_sVideoParam, "Code"), 0);
		int iOldMode = ParseInt(m_Node.omlGetContent(m_sVideoParam, "Mode"), 0);
		int iOldRate = ParseInt(m_Node.omlGetContent(m_sVideoParam, "Rate"), 0);
		int iOldCameraNo = ParseInt(m_Node.omlGetContent(m_sVideoParam, "CameraNo"), 0);
		int iOldBitRate = ParseInt(m_Node.omlGetContent(m_sVideoParam, "BitRate"), 0);
		int iOldMaxStream = ParseInt(m_Node.omlGetContent(m_sVideoParam, "MaxStream"), 0);
		int iOldPortrait = ParseInt(m_Node.omlGetContent(m_sVideoParam, "Portrait"), 0);
		int iOldForward = ParseInt(m_Node.omlGetContent(m_sVideoParam, "Forward"), 0);
		int iOldFilterDecode = ParseInt(m_Node.omlGetContent(m_sVideoParam, "FilterDecode"), 0);
		
		String sParamTemp = "(Code){" + iCode + "}(Mode){" + iMode + "}(Rate){" + iRate
			+ "}(CameraNo){" + iCameraNo + "}(BitRate){" + iBitRate + "}(MaxStream){" + iMaxStream + "}";
		sParamTemp += "(Portrait){" + iOldPortrait + "}(Forward){" + iOldForward + "}(FilterDecode){" + iOldFilterDecode + "}";

		boolean bResult = false;
		if (iCode != iOldCode
			|| iMode != iOldMode
			|| iRate != iOldRate 
			|| iCameraNo != iOldCameraNo
			|| iBitRate != iOldBitRate
			|| iMaxStream != iOldMaxStream)
		{
			do {
				if (m_bStarted && m_bApiVideoStart) {
					VideoClean(false);
				}

				if (iCameraNo != iOldCameraNo || iBitRate != iOldBitRate || iRate != iOldRate) {
					if (!VideoOption(sParam)) {
						OutString("pgLibLive.VideoParam: set video option failed");
						break;
					}
				}

				m_sVideoParam = sParamTemp;
				if (m_bStarted && m_bApiVideoStart) {
					if (!VideoInit(false)) {
						OutString("pgLibLive.VideoParam: Video init failed");
						break;
					}
				}

				bResult = true;
			}
			while (false);
		}
		else {
			m_sVideoParam = sParamTemp;
			bResult = true;
		}

		if (bResult) {
			int iForward = ParseInt(m_Node.omlGetContent(m_sVideoParam, "Forward"), 0);
			if (iForward != 0) {
				ForwardRequest(true);
			}
		}

		return bResult;
	}

	public boolean VideoRecordStart(String sAviPath) {
		if (m_Node == null) {
			OutString("pgLibLive.VideoRecordStart: Node object is null!");
			return false;
		}
		
		if (sAviPath.lastIndexOf(".avi") <= 0) {
			OutString("pgLibLive.VideoRecordStart: invalid avi path. sAviPath=" + sAviPath);
			return false;
		}
	
		String sData = "(Path){" + m_Node.omlEncode(sAviPath)  + "}";
		int iErr = m_Node.ObjectRequest(("thisLive_" + m_sDevID),
			36, sData, "pgLibLive.VideoRecordStart");
		if (iErr > 0) {
			OutString("pgLibLive.VideoRecordStart: iErr=" + iErr);
			return false;
		}
		
		return true;
	}

	public void VideoRecordStop() {
		if (m_Node == null) {
			OutString("pgLibLive.VideoRecordStop: Node object is null!");
			return;
		}

		int iErr = m_Node.ObjectRequest(("thisLive_" + m_sDevID),
			36, "(Path){}", "pgLibLive.VideoRecordStop");
		if (iErr != 0) {
			OutString("pgLibLive.VideoRecordStop: iErr=" + iErr);
		}
	}

	public boolean AudioSpeech(boolean bEnable) {
		if (m_Node == null) {
			OutString("pgLibLive.AudioSpeech: Node object is null!");
			return false;
		}

		if (!m_bApiAudioStart) {
			OutString("pgLibLive.AudioSpeech: Not audio start!");
			return false;
		}

		if (m_iMode != pgLibLiveMode.Render) {
			OutString("pgLibLive.AudioSpeech: Only valid at render side.");
			return false;
		}

		int iEnable = bEnable ? 1 : 0;
		String sData = "(Peer){" + m_sObjCap + "}(ActSelf){" + iEnable + "}(ActPeer){1}";
		int iErr = m_Node.ObjectRequest("thisAudio_" + m_sDevID, 36, sData, "pgLibLive.AudioSpeech");
		if (iErr > 0) {
			OutString("pgLibLive.AudioSpeech: Set audio speech, iErr=" + iErr);
			return false;
		}

		return true;
	}
	
	public boolean AudioParam(String sParam) {

		m_sAudioParam = sParam;
		if (m_Node == null) {
			return true;
		}
		
		int iReliable = ParseInt(m_Node.omlGetContent(m_sAudioParam, "Reliable"), 0);
		if (iReliable == 0) {
			return true;
		}

		String sData = "(Item){8}(Value){1}";
		int iErr = m_Node.ObjectRequest(("thisAudio_" + m_sDevID), 2, sData, "pgLibLive.AudioParam");
		if (iErr > 0) {
			// Set failed.
		}

		return true;
	}

	public boolean RecordStart(String sAviPath, boolean bVideo, boolean bAudio) {
		if (m_Node == null) {
			OutString("pgLibLive.RecordStart: Node object is null!");
			return false;
		}

		if (m_iMode != pgLibLiveMode.Render) {
			OutString("pgLibLive.RecordStart: Only valid at render side.");
			return false;
		}

		if (sAviPath.lastIndexOf(".avi") <= 0) {
			OutString("pgLibLive.RecordStart: invalid avi path. sAviPath=" + sAviPath);
			return false;
		}
	
		if (bVideo) {
			String sData = "(Path){" + m_Node.omlEncode(sAviPath)  + "}";
			int iErr = m_Node.ObjectRequest(("thisLive_" + m_sDevID),
				36, sData, "pgLibLive.RecordStartVideo");
			if (iErr > 0) {
				OutString("pgLibLive.RecordStartVideo: iErr=" + iErr);
				return false;
			}
		}

		if (bAudio) {
			String sData = "(Peer){" + m_sObjCap + "}(Path){" + m_Node.omlEncode(sAviPath)  + "}";
			int iErr = m_Node.ObjectRequest(("thisAudio_" + m_sDevID),
				37, sData, "pgLibLive.RecordStartAudio");
			if (iErr > 0) {
				m_Node.ObjectRequest(("thisLive_" + m_sDevID),
					36, "(Path){}", "pgLibLive.RecordStopVideo");

				OutString("pgLibLive.RecordStartAudio: iErr=" + iErr);
				return false;
			}
		}
		
		return true;
	}

	public void RecordStop() {
		if (m_Node == null) {
			OutString("pgLibLive.RecordStop: Node object is null!");
			return;
		}

		if (m_iMode != pgLibLiveMode.Render) {
			OutString("pgLibLive.RecordStop: Only valid at render side.");
			return;
		}

		int iErr = m_Node.ObjectRequest(("thisLive_" + m_sDevID),
			36, "(Path){}", "pgLibLive.RecordStopVideo");
		if (iErr > 0) {
			OutString("pgLibLive.RecordStopVideo: iErr=" + iErr);
		}

		iErr = m_Node.ObjectRequest(("thisAudio_" + m_sDevID),
			37, "(Peer){" + m_sObjCap + "}(Path){}", "pgLibLive.RecordStopAudio");
		if (iErr > 0) {
			OutString("pgLibLive.RecordStopAudio: iErr=" + iErr);
		}
	}

	public boolean SvrRequest(String sData) {
		if (m_Node == null) {
			OutString("pgLibLive.SvrRequest: Not initialize");						
			return false;
		}

		int iErr = m_Node.ObjectRequest(m_sObjSvr, 35, ("1024:" + sData), "pgLibLive.SvrRequest");
		if (iErr > 0) {
			OutString("pgLibLive.SvrRequest: iErr=" + iErr);			
			return false;
		}

		return true;
	}
	
	public boolean ServerNetMode(int iMode) {
		if (m_Node == null) {
			OutString("pgLibLive.ServerNetMode: Node object is null!");
			return false;
		}

		if (iMode < 0 || iMode > 2) {
			OutString("pgLibLive.ServerNetMode: iMode can only is: 0, 1, 2");
			return false;
		}

		String sCls = m_Node.ObjectGetClass(m_sObjSvr);
		if (sCls.equals("")) {
			OutString("pgLibLive.ServerNetMode: Server peer object is invalid");			
			return false;
		}

		int iErr = m_Node.ObjectRequest(m_sObjSvr, 2, "(Item){4}(Value){" + iMode + "}", "");
		if (iErr > 0) {
			OutString("pgLibLive.ServerNetMode: iErr=" + iErr);			
			return false;
		}

		return true;
	}

	public int ForwardAlloc() {
		if (m_Node == null) {
			OutString("pgLibLive.ForwardAlloc: Node object is null!");
			return PG_ERR_BadStatus;
		}

		int iErr = ForwardRequest(true);
		if (iErr == PG_ERR_Normal) {
			m_sVideoParam = m_Node.omlDeleteEle(m_sVideoParam, "Forward", 1, 0);
			m_sVideoParam += "(Forward){1}";
		}

		return iErr;
	}

	public int ForwardFree() {
		if (m_Node == null) {
			OutString("pgLibLive.ForwardFree: Node object is null!");
			return PG_ERR_BadStatus;
		}

		int iErr = ForwardRequest(false);
		m_sVideoParam = m_Node.omlDeleteEle(m_sVideoParam, "Forward", 1, 0);
		m_sVideoParam += "(Forward){0}";

		return iErr;
	}

	public int FileGetRequest(String sPeer, String sPath, String sPeerPath) {
		return FileRequest(sPeer, 33, sPath, sPeerPath);
	}

	public int FilePutRequest(String sPeer, String sPath, String sPeerPath) {
		return FileRequest(sPeer, 32, sPath, sPeerPath);
	}

	public int FileAccept(String sPeer, String sPath) {
		return FileRequestAccept(sPeer, sPath);
	}

	public int FileReject(String sPeer) {
		return FileRequestRefuse(sPeer);
	}

	public void FileCancel(String sPeer) {
		if (m_Node == null) {
			return;
		}

		if (sPeer.equals("") && m_iMode == pgLibLiveMode.Render) {
			sPeer = m_sObjSelf;
		}
		if (sPeer.equals("")) {
			return;
		}

		int iErr = m_Node.ObjectRequest("File_" + sPeer, 35, "", "pgLibLive.FileCancel");
		OutString("pgLibLive.FileCancel:" + iErr);
		m_iFileFlag = 0;
	}


	///------------------------------------------------------------------------
	// Static function

	public static int ParseInt(String sInt, int iDef) {
		try {
			if (sInt.equals("")) {
				return iDef;
			}
			return Integer.parseInt(sInt);
		}
		catch (Exception ex) {
			return iDef;
		}
	}
	
	public static void OutString(String sOut) {
		if (s_bOutString) {
			System.out.println(sOut);
		}
	}
	
	public static void OutStringEnable(boolean bEnable) {
		s_bOutString = bEnable;
	}


	///------------------------------------------------------------------------
	// PG Node callback class.
	class pgLibLiveNodeProc extends pgLibJNINodeProc {
		public pgLibLiveNodeProc() {
			super();
		}
		public int OnReply(String sObj, int uErrCode, String sData, String sParam) {
			return NodeOnReply(sObj, uErrCode, sData, sParam);
		}
		public int OnExtRequest(String sObj, int uMeth, String sData, int uHandle, String sPeer) {
			return NodeOnExtRequest(sObj, uMeth, sData, uHandle, sPeer);
		}
	}


	///------------------------------------------------------
	// Private member.
	
	private static final String LIVE_VER = "20";

	private static final int VIDEO_CODE_Butt = 4;
	private static final int VIDEO_MODE_Butt = 11;

	private HashMap<String, String> handleList = new HashMap<String,String>();

	private static Integer s_iNodeLibInitCount = 0;
	private static boolean s_bOutString = true;
	
	// Randomer.
	private java.util.Random m_Random = new java.util.Random();

	// Event listen interface object.
	private OnEventListener m_eventListener = null;

	// PG Node object.
	public pgLibJNINode m_Node = null;
	public pgLibLiveNodeProc m_NodeProc = null;
	
	// Init status
	private boolean m_bInited = false;

	// Store init parameters
	private int m_iMode = 0;
	private String m_sUser = "";
	private String m_sPass = "";
	private String m_sRelayAddr = "";
	private String m_sVideoParam = "";
	private int m_iP2PTryTime = 0;
	private String m_sObjCap = "";
	private String m_sGroupName = "";
	private String m_sAudioParam = "";

	// Server parameters
	private String m_sInitSvrName = "pgConnectSvr";
	private String m_sInitSvrAddr = "";

	// Store status.
	private String m_sObjSvr = "";
	private String m_sObjSelf = "";
	private String m_sSvrAddr = "";
	private boolean m_bEventEnable = false;
	private boolean m_bStarted = false;
	private boolean m_bLogin = false;
	private boolean m_bCapPeerCheckTimer = false;
	private String m_sLanScanRes = "";
	private String m_sCapAddr = "";
	private String m_sDevID = "";

	// API status parameters
	private boolean m_bApiStart = false;
	private boolean m_bApiVideoStart = false;
	private boolean m_bApiAudioStart = false;
	private boolean m_bApiLanScan = false;
	private int m_iFileFlag = 0;

	// Render info list.
	class RenderInfo {
		public String sPeer = "";
		public int iStamp = 0;
		public RenderInfo(String sPeer, int iStamp) {
			this.sPeer = sPeer;
			this.iStamp = iStamp;
		}
	}

	ArrayList<RenderInfo> m_listRender = null; 
	private int m_iActiveStamp = 0;


	///------------------------------------------------------------------------
	// Private methods.
	
	private static boolean NodeLibInit(Context oCtx) {
		try {
			boolean bResult = false;
			synchronized(s_iNodeLibInitCount) {
				if (s_iNodeLibInitCount > 0) {
					s_iNodeLibInitCount++;
					bResult = true;
				}
				else {
					if (pgLibJNINode.Initialize(oCtx)) {
						s_iNodeLibInitCount++;
						bResult = true;
					}
				}
			}
			return bResult;
		}
		catch (Exception e) {
			OutString("pgLibLive.NodeLibInit: e=" + e.toString());
			return false;
		}
	}
	
	private static void NodeLibClean() {
		try {
			synchronized(s_iNodeLibInitCount) {
				if (s_iNodeLibInitCount > 0) {
					s_iNodeLibInitCount--;
					if (s_iNodeLibInitCount == 0) {
						pgLibJNINode.Clean();
					}
				}
			}
		}
		catch (Exception e) {
			OutString("pgLibLive.NodeLibClean: e=" + e.toString());
		}		
	}

	private void TimerProc(String sParam) {
		String sAct = m_Node.omlGetContent(sParam, "Act");
		if (sAct.equals("CapTimerActive")) {
			CapTimerActive();
		}
		else if (sAct.equals("CapPeerCheck")) {
			CapPeerCheckTimeout();
		}
		else if (sAct.equals("Relogin")) {
			NodeLogin();
		}
	}

	private void EventProc(String sAct, String sData, String sRender) {
		if (m_eventListener != null && m_bEventEnable) {
			m_eventListener.event(sAct, sData, sRender);
		}
	}

	private boolean NodeStart() {
		if (m_Node == null) {
			return false;
		}

		// Select server parameters.
		m_sObjSvr = m_sInitSvrName;
		m_sSvrAddr = m_sInitSvrAddr;

		// Config atx node.
		m_Node.Control = "Type=1;LogLevel0=1;LogLevel1=1";
		m_Node.Node = "Type=0;Option=1;P2PTryTime=" + m_iP2PTryTime;
		m_Node.Class = "PG_CLASS_Data:4;PG_CLASS_File:16;PG_CLASS_Video:4;PG_CLASS_Audio:4;PG_CLASS_Live:4";
		m_Node.Local = "Addr=0:0:0:127.0.0.1:0:0";
		m_Node.Server = "Name=" + m_sObjSvr + ";Addr=" + m_sSvrAddr + ";Digest=1";
		m_Node.NodeProc = m_NodeProc;
		if (!m_sRelayAddr.equals("")) {
			m_Node.Relay = "(Relay0){(Type){0}(Load){0}(Addr){" + m_sRelayAddr + "}}";
		}
		else {
			int iInd = m_sSvrAddr.lastIndexOf(':');
			if (iInd > 0) {
				String sSvrIP = m_sSvrAddr.substring(0, iInd);
				m_Node.Relay = "(Relay0){(Type){0}(Load){0}(Addr){" + sSvrIP + ":443}}";
			}
		}

		// Start atx node.
		if (!m_Node.Start(0)) {
			OutString("pgLibLive.NodeStart: Start node failed.");
			return false;
		}
		
		// Enable to report event
		m_bEventEnable = true;

		// Enable video input external
		NodeVideoExter();

		// Login to server.
		if (!NodeLogin()) {
			OutString("pgLibLive.NodeStart: login failed.");
			NodeStop();
			return false;
		}

		// Enable LAN scan.
		String sValue = "(Enable){1}(Peer){" + m_Node.omlEncode(m_sObjSelf) + "}(Label){pgLive}";
		String sData = "(Item){1}(Value){" + m_Node.omlEncode(sValue) + "}";
		int iErr = m_Node.ObjectRequest(m_sObjSvr, 2, sData, "pgLibLive.EnableLanScan");
		if (iErr > 0) {
			OutString("pgLibLive.NodeStart: Enable lan scan failed. iErr=" + iErr);
		}

		// Start api service.
		if (m_bApiStart) {
			ServiceStart();
		}

		if (m_bApiVideoStart) {
			VideoInit(true);
		}

		if (m_bApiAudioStart) {
			AudioInit();
		}

		return true;
	}

	private void NodeStop() {
		if (m_Node == null) {
			return;
		}

		m_bApiLanScan = false;

		ServiceStop();
		NodeLogout();

		m_bEventEnable = false;
		m_Node.Stop();
	}

	private void NodeVideoExter() {
		if (m_Node == null) {
			return;
		}

		int iVideoInExternal = ParseInt(m_Node.omlGetContent(m_sVideoParam, "VideoInExternal"), 0);
		int iInputExternal = ParseInt(m_Node.omlGetContent(m_sVideoParam, "InputExternal"), 0);
		int iOutputExternal = ParseInt(m_Node.omlGetContent(m_sVideoParam, "OutputExternal"), 0);
		int iOutputExtCmp = ParseInt(m_Node.omlGetContent(m_sVideoParam, "OutputExtCmp"), 0);

		if (iVideoInExternal != 0
			|| iInputExternal != 0
			|| iOutputExternal != 0
			|| iOutputExtCmp != 0)
		{
			if (m_Node.ObjectAdd("_vTemp", "PG_CLASS_Video", "", 0)) {

				if (iVideoInExternal != 0 || iInputExternal != 0) {
					m_Node.ObjectRequest("_vTemp", 2, "(Item){8}(Value){1}", "");
				}

				if (iOutputExtCmp != 0) {
					m_Node.ObjectRequest("_vTemp", 2, "(Item){13}(Value){1}", "");
				}
				else if (iOutputExternal != 0) {
					m_Node.ObjectRequest("_vTemp", 2, "(Item){11}(Value){1}", "");
				}

				m_Node.ObjectDelete("_vTemp");
			}
		}
	}

	private boolean NodeLogin() {
		if (m_Node == null) {
			return false;
		}

		String sVersion = "";
		String sVerTemp = m_Node.omlGetContent(m_Node.utilCmd("Version", ""), "Version");
		if (sVerTemp.length() > 1) {
			sVersion = sVerTemp.substring(1);
		}

		String sParam = "(Ver){" + sVersion + "." + LIVE_VER + "}";
		String sData = "(User){" + m_Node.omlEncode(m_sObjSelf) + "}(Pass){"
			+ m_Node.omlEncode(m_sPass) + "}(Param){" + m_Node.omlEncode(sParam) + "}";
		int iErr = m_Node.ObjectRequest(m_sObjSvr, 32, sData, "pgLibLive.NodeLogin");
		if (iErr > 0) {
			OutString("pgLibLive.NodeLogin: Login failed. iErr=" + iErr);
			return false;
		}

		return true;
	}

	private void NodeLogout() {
		if (m_Node == null) {
			return;
		}

		if (m_iMode == pgLibLiveMode.Capture && m_bLogin) {
			int iForward = ParseInt(m_Node.omlGetContent(m_sVideoParam, "Forward"), 0);
			if (iForward != 0) {
				ForwardRequest(false);
			}
		}

		m_Node.ObjectRequest(m_sObjSvr, 33, "", "pgLibLive.NodeLogout");
		if (m_bLogin) {
			EventProc("Logout", "", "");
		}

		m_bLogin = false;
	}
	
	private void NodeRelogin(int iDelay) {
		NodeLogout();
		TimerStart("(Act){Relogin}", iDelay, false);
	}

	private void NodeRedirect(String sRedirect) {
		if (m_Node == null) {
			return;
		}

		NodeLogout();

		String sSvrName = m_Node.omlGetContent(sRedirect, "SvrName");
		if (!sSvrName.equals("") && !sSvrName.equals(m_sObjSvr)) {
			m_Node.ObjectDelete(m_sObjSvr);
			if (!m_Node.ObjectAdd(sSvrName, "PG_CLASS_Peer", "", (0x10000 | 0x2))) {
				OutString("pgLibLive.NodeRedirect: Add server object failed");
				return;
			}
			m_sObjSvr = sSvrName;
			m_sSvrAddr = "";
		}
		String sSvrAddr = m_Node.omlGetContent(sRedirect, "SvrAddr");
		if (!sSvrAddr.equals("") && !sSvrAddr.equals(m_sSvrAddr)) {
			String sData = "(Addr){" + sSvrAddr + "}(Proxy){}";
			int iErr = m_Node.ObjectRequest(m_sObjSvr, 37, sData, "pgLibLive.NodeRedirect");
			if (iErr > 0) {
				OutString("pgLibLive.NodeRedirect: Set server address. iErr=" + iErr);
				return;
			}
			m_sSvrAddr = sSvrAddr;
		}

		OutString("pgLibLive.NodeRedirect: sSvrName=" + sSvrName + ", sSvrAddr=" + sSvrAddr);

		TimerStart("(Act){Relogin}", 1, false);
	}

	private void NodeRedirectReset(int iDelay) {
		if (!m_sSvrAddr.equals(m_sInitSvrAddr)) {
			String sRedirect = "(SvrName){" + m_sInitSvrName
				+ "}(SvrAddr){" + m_sInitSvrAddr + "}";
			NodeRedirect(sRedirect);
		}
		else {
			if (iDelay != 0) {
				NodeRelogin(iDelay);
			}
		}
	}

	private int NodeLoginReply(int iErr, String sData) {
		if (m_Node == null) {
			return 1;
		}

		if (iErr != 0) {
			OutString("pgLibLive.NodeLoginReply: Login failed. uErr=" + iErr);

			EventProc("Login", String.valueOf(iErr), "");
			if (iErr == 11 || iErr == 12 || iErr == 14) {
				NodeRedirectReset(10);
			}

			return 1;
		}
		
		String sParam = m_Node.omlGetContent(sData, "Param");
		String sRedirect = m_Node.omlGetEle(sParam, "Redirect.", 10, 0);
		if (!sRedirect.equals("")) {
			NodeRedirect(sRedirect);
			return 1;
		}

		m_bLogin = true;
		CapPeerCheck();

		int iForward = ParseInt(m_Node.omlGetContent(m_sVideoParam, "Forward"), 0);
		if (iForward != 0) {
			ForwardRequest(true);
		}

		EventProc("Login", "0", "");
		return 1;
	}

	private int ForwardRequest(boolean bAction) {
		if (m_Node == null) {
			return PG_ERR_BadStatus;
		}

		if (m_iMode != pgLibLiveMode.Capture) {
			return PG_ERR_Reject;
		}

		int iErr;
		if (bAction) {
			iErr = m_Node.ObjectRequest(m_sObjSvr, 35, "2049:(Capture){"
				+ m_sDevID + "}", "pgLibLive.ForwardAlloc");
		}
		else {
			iErr = m_Node.ObjectRequest(m_sObjSvr, 35, "2050:(Capture){"
				+ m_sDevID + "}", "pgLibLive.ForwardFree");
		}

		return iErr;
	}

	private int FileRequest(String sPeer, int iMethod, String sPath, String sPeerPath) {
		if (m_Node == null) {
			return PG_ERR_BadStatus;
		}

		if (m_iFileFlag != 0 && m_iMode == pgLibLiveMode.Render) {
			return PG_ERR_BadStatus;
		}

		if (sPeer.equals("") && m_iMode == pgLibLiveMode.Render) {
			sPeer = m_sObjSelf;
		}

		String sParam = (iMethod == 32 ? "pgLibLive.FilePutRequest" : "pgLibLive.FileGetRequest");
		int iErr = m_Node.ObjectRequest("File_" + sPeer, iMethod,
			"(Path){" + m_Node.omlEncode(sPath) + "}(PeerPath){"
			+ m_Node.omlEncode(sPeerPath) + "}(TimerVal){1}(Offset){0}(Size){0}", sParam);
		Log.d("error", "pgLibLive.FileRequest iErr:" + iErr);
		if (iErr > 0) {
			return iErr;
		}

		m_iFileFlag = 1;
		return PG_ERR_Normal;
	}

	private int FileRequestAccept(String sPeer, String sPath) {
		return FileReply(0, sPeer, sPath);
	}

	private int FileRequestRefuse(String sPeer) {
		return FileReply(1, sPeer, "");
	}

	private int FileReply(int uAction, String sPeer, String sPath) {
		if (m_Node == null) {
			return PG_ERR_BadStatus;
		}

		int iErrReply = 0;
		String sData = "";
		if (uAction != 1) {
			iErrReply = 0;
			sData = "(Path){" + m_Node.omlEncode(sPath) + "}(TimerVal){1}";
			m_iFileFlag = 1;
		}
		else {
			iErrReply = 13;
			m_iFileFlag = 0;
		}

		OutString("filereply:" + sPeer + "," + sData);
		String sHandle = handleList.get(sPeer);
		if (sHandle == null) {
			return PG_ERR_BadParam;
		}

		int iHandle = ParseInt(sHandle, 0);
		int iErr = m_Node.ObjectExtReply("File_" + sPeer, iErrReply, sData, iHandle);
		if (iErr != 0) {
			return iErr;
		}

		handleList.remove(sPeer);
		return iErr;
	}

	private void doFileCancel(String sPeer) {
		OutString("doFileCancel");
		if (sPeer.equals("")) {
			return;
		}

		m_iFileFlag = 0;
		EventProc("FileAbort", "", sPeer);
	}

	private boolean ServiceStart() {
		if (m_Node == null) {
			return false;
		}

		if (m_iMode == pgLibLiveMode.Capture) {
			if (!m_Node.ObjectAdd(m_sGroupName, "PG_CLASS_Group", "", (0x10000 | 0x10 | 0x4 | 0x1 | 0x40))) {
				OutString("pgLibLive.ServiceStart: Add 'thisGroup' failed.");
				return false;
			}

			int iMask = 0x0200; // Tell all.
			String sDataMdf = "(Action){1}(PeerList){(" + m_sObjSelf + "){" + iMask + "}}";
			m_Node.ObjectRequest(m_sGroupName, 32, sDataMdf, "");
			
			int iIDTimer = TimerStart("(Act){CapTimerActive}", 10, false);
			if (iIDTimer < 0) {
				return false;
			}

			m_iActiveStamp = 0;
		}
		else {
			// Delete the old capture peer object.
			m_Node.ObjectDelete(m_sObjCap);
			m_sCapAddr = "";

			// Add capture peer object.
			if (!CapPeerAdd(false)) {
				OutString("pgLibLive.ServiceStart: Add '" + m_sObjCap + "' failed.");
				return false;
			}

			// Add group object.
			if (!m_Node.ObjectAdd(m_sGroupName, "PG_CLASS_Group", m_sObjCap, (0x10000 | 0x10 | 0x1 | 0x40))) {
				OutString("pgLibLive.ServiceStart: Add 'thisGroup' failed.");
				return false;
			}

			String sPeer = m_sObjSelf;
			if (!m_Node.ObjectAdd("File_" + sPeer, "PG_CLASS_File", m_sObjCap, 0x10000)) {
				Log.d("error", "pgLibLive.ServiceStart: Add File" + m_sObjSelf + " failed.");
				return false;
			}
		}

		// Add data object use to transfer message.
		if (!m_Node.ObjectAdd("thisData_" + m_sDevID, "PG_CLASS_Data", m_sGroupName, 0x10000)) {
			OutString("pgLibLive.ServiceStart: Add 'thisData' failed.");
			return false;
		}

		m_listRender = new ArrayList<RenderInfo>();

		m_bStarted = true;
		return true;
	}
	
	private void ServiceStop() {
		if (m_Node == null) {
			return;
		}

		m_bStarted = false;
		m_listRender = null;

		if (m_bApiVideoStart) {
			VideoClean(true);
		}
		if (m_bApiAudioStart) {
			AudioClean();
		}

		m_bApiVideoStart = false;
		m_bApiAudioStart = false;
		
		String sDataMdf = "(Action){0}(PeerList){(" + m_Node.omlEncode(m_sObjSelf) + "){0}}";
		m_Node.ObjectRequest(m_sGroupName, 32, sDataMdf, "");

		m_Node.ObjectDelete("thisData_" + m_sDevID);
		m_Node.ObjectDelete(m_sGroupName);

		if (!m_sObjCap.equals("")) {
			m_Node.ObjectDelete(m_sObjCap);
			m_sCapAddr = "";
		}
	}
	
	private RenderInfo RenderSearch(String sPeer) {
		if (m_listRender != null) {
			for (int i = 0; i < m_listRender.size(); i++) {
				if (m_listRender.get(i).sPeer.equals(sPeer)) {
					return m_listRender.get(i);
				}
			}
		}
		return null;
	}

	private void RenderAdd(String sPeer) {
		try {
			RenderInfo info = RenderSearch(sPeer);
			if (info != null) {
				info.iStamp = m_iActiveStamp;
			}
			else {
				if (m_listRender != null) {
					m_listRender.add(new RenderInfo(sPeer, m_iActiveStamp));
					EventProc("RenderJoin", sPeer, sPeer);
				}
			}
		}
		catch (Exception ex) {
	        ex.printStackTrace();
		}
	}
	
	private void RenderDelete(String sPeer) {
		if (m_listRender != null) {
			for (int i = 0; i < m_listRender.size(); i++) {
				if (m_listRender.get(i).sPeer.equals(sPeer)) {
					EventProc("RenderLeave", "reason=0", sPeer);
					m_listRender.remove(i);
					break;
				}
			}
		}
	}
	
	private boolean CameraSwitch(int iCameraNo) {
		if (m_Node == null) {
			return false;
		}

		if (m_Node.ObjectAdd("CameraPrvw", "PG_CLASS_Video", "", 0x2)) {
			m_Node.ObjectRequest("CameraPrvw", 2, "(Item){0}(Value){" + iCameraNo + "}", "");
			m_Node.ObjectDelete("CameraPrvw");
			return true;
		}

		return false;
	}

	// Set capture extend option.
	private void VidepOptionBase() {
		if (m_Node == null) {
			return;
		}

		if (m_Node.ObjectAdd("_vTemp", "PG_CLASS_Video", "", 0)) {

			// Set capture portrait
			String sTemp = m_Node.omlGetContent(m_sVideoParam, "Portrait");
			if (sTemp.equals("1")) {
				if (m_Node.ObjectRequest("_vTemp", 2, "(Item){2}(Value){90}", "") == 0) {
					OutString("pgLibLive.VideoStart. Set capture portrait");
				}
			}

			m_Node.ObjectDelete("_vTemp");
		}
	}

	private boolean VideoPreview() {
		if (m_Node == null) {
			return false;
		}

		try {
			if (m_Node.ObjectAdd("Prvw", "PG_CLASS_Video", "", 0x2)) {

				int iMode = ParseInt(m_Node.omlGetContent(m_sVideoParam, "Mode"), 2);
				if (iMode < 2) {
					iMode = 2;
				}

				String sWndRect = "(Code){0}(Mode){" + iMode + "}(Rate){40}(Wnd){}";
				m_Node.ObjectRequest("Prvw", 32, sWndRect, "pgLibLive.PrvwStart");
	
				Thread.sleep(50);
				return true;
			}

			return false;
		}
		catch (Exception ex) {
			OutString("pgLibLive.VideoPreview. Add preview failed");
			return false;
		}
	}

	private boolean CapPeerAdd(boolean bStatic) {
		if (m_Node == null) {
			return false;
		}

		if (m_iMode != pgLibLiveMode.Render) {
			return false;
		}
		if (m_sObjCap.equals("")) {
			return false;
		}

		m_sCapAddr = "";
		m_Node.ObjectDelete(m_sObjCap);

		boolean bAddSuccess = false;
		if (!m_bLogin || bStatic) {
			String sEle = m_Node.omlGetEle(m_sLanScanRes, m_sObjCap, 1, 0);
			if (!sEle.equals("")) {
				if (m_Node.ObjectAdd(m_sObjCap, "PG_CLASS_Peer", "", (0x10000 | 0x4))) {
					// Set static peer's address.
					String sAddr = m_Node.omlGetContent(sEle, "");
					String sData = "(Type){0}(Addr){0:0:0:" + sAddr + ":0}(Proxy){}";
					if (m_Node.ObjectRequest(m_sObjCap, 37, sData, "pgLibLive.SetAddr") <= 0) {
						OutString("pgLibLive.CapPeerAdd: Set '" + m_sObjCap + "' in static.");
						m_sCapAddr = sAddr;
						bAddSuccess = true;
					}
					else {
						OutString("pgLibLive.CapPeerAdd: Set '" + m_sObjCap + "' address failed.");
					}
				}
				else {
					OutString("pgLibLive.CapPeerAdd: Add '" + m_sObjCap + "' with static flag failed.");
				}	
			}
		}

		if (!bAddSuccess) {
			if (m_Node.ObjectAdd(m_sObjCap, "PG_CLASS_Peer", "", 0x10000)) {
				OutString("pgLibLive.CapPeerAdd: Add '" + m_sObjCap + "' without static flag.");
				bAddSuccess = true;
			}
			else {
				OutString("pgLibLive.CapPeerAdd: Add '" + m_sObjCap + "' failed.");
			}
		}

		return bAddSuccess;
	}

	private void CapPeerCheck() {
		if (m_Node == null) {
			return;
		}

		if (m_iMode != pgLibLiveMode.Render) {
			return;
		}

		int iErr = m_Node.ObjectRequest(m_sObjCap, 41, "(Check){1}(Value){3}(Option){}", "");
		if (iErr <= 0) {
			m_Node.ObjectRequest(m_sObjCap, 36, "Active?", "pgLibLive.MessageSend");
			return;
		}
		if (iErr == 5) {
			CapPeerAdd(false);
		}
		else {
			m_Node.ObjectSync(m_sObjCap, "", 1);
		}
	}
	
	private void CapPeerCheckTimeout() {
		CapPeerCheck();
		if (m_bCapPeerCheckTimer) {
			TimerStart("(Act){CapPeerCheck}", 5, false);
		}
		OutString("pgLibLive.CapPeerCheckTimeout: ObjCap = " + m_sObjCap);
	}

	private void CapPeerStatic() {
		if (m_Node == null) {
			return;
		}

		if (m_iMode != pgLibLiveMode.Render) {
			return;
		}
		if (!m_bStarted) {
			return;
		}

		String sEle = m_Node.omlGetEle(m_sLanScanRes, m_sObjCap, 1, 0);
		if (!sEle.equals("")) {
			String sAddr = m_Node.omlGetContent(sEle, "");
			if (!sAddr.equals(m_sCapAddr)) {
				CapPeerAdd(true);
			}
		}
	}
	
	private void CapTimerActive() {
		if (m_Node == null) {
			return;
		}

		if (m_iMode != pgLibLiveMode.Capture) {
			return;
		}
		if (!m_bStarted) {
			m_iActiveStamp = 0;
			return;
		}
	
		m_iActiveStamp += 10;
		TimerStart("(Act){CapTimerActive}", 10, false);

		if (m_listRender == null) {
			return;
		}

		int i = 0;
		while (i < m_listRender.size()) {
			RenderInfo info = m_listRender.get(i);
			if ((m_iActiveStamp - info.iStamp) > 30) {
				EventProc("RenderLeave", "reason=1", info.sPeer);
				m_listRender.remove(i);
				continue;
			}
			i++;
		}

		for (i = 0; i < m_listRender.size(); i++) {
			RenderInfo info = m_listRender.get(i);
			m_Node.ObjectRequest(info.sPeer, 36, "Active?", "pgLibLive.MessageSend");
		}
	}

	private boolean VideoInit(boolean bPreview) {
		if (m_Node == null) {
			return false;
		}

		if (m_iMode == pgLibLiveMode.Capture) {
			VidepOptionBase();
			VideoOption("");
			if (bPreview) {
				VideoPreview();
			}
		}
		else {
			CapPeerCheck();
		}

		String sFilterDec = m_Node.omlGetContent(m_sVideoParam, "FilterDecode");
		int iFilterDec = ParseInt(sFilterDec, 0);

		int iAddFlag = (0x10000 | 0x2 | 0x04 | 0x08);
		if (iFilterDec != 0) {
			iAddFlag |= 0x10;
		}

		if (!m_Node.ObjectAdd("thisLive_" + m_sDevID, "PG_CLASS_Live", m_sGroupName, iAddFlag)) {
			OutString("pgLibLive.NodeLoginReply: Add 'thisLive_" + m_sDevID + "' failed.");
			return false;
		}

		String sMaxStream = m_Node.omlGetContent(m_sVideoParam, "MaxStream");
		int iMaxStream = ParseInt(sMaxStream, 0);

		String sData = "";
		if (m_iMode == pgLibLiveMode.Capture) {
			String sCode = m_Node.omlGetContent(m_sVideoParam, "Code");
			String sMode = m_Node.omlGetContent(m_sVideoParam, "Mode");
			String sRate = m_Node.omlGetContent(m_sVideoParam, "Rate");

			int iFrmRate = ParseInt(sRate, 40);

			int iDelay = ParseInt(m_Node.omlGetContent(m_sVideoParam, "Delay"), 300);
			if (iDelay < 300) {
				iDelay = 300;
			}

			int iCacheSize = 0;
			if (iFrmRate > 0) {
				iCacheSize = (iDelay / iFrmRate) + 40;
			}
			if (iCacheSize < 80) {
				iCacheSize = 80;
			}

			String sVideoParam = "(Code){" + sCode + "}(Mode){" + sMode + "}(Rate){" + sRate + "}";		

			sData = "(Source){1}(Media){1}(Delay){" + iDelay + "}(CacheSize){" + iCacheSize
				+ "}(MaxPart){1}(TimerVal){1}(Param){" + m_Node.omlEncode(sVideoParam) + "}";
			
			if (iMaxStream == 0) {
				iMaxStream = 2;
			}
		}
		else {
			String sWnd = "(Wnd){" + m_Node.utilGetWndRect() + "}";
			sData = "(Source){0}(Media){1}(Delay){300}(CacheSize){80}(MaxPart){1}(TimerVal){1}(Param){"
				+ m_Node.omlEncode(sWnd) + "}";
		}

		String sData1 = "(Item){0}(Value){" + iMaxStream + "}";
		m_Node.ObjectRequest("thisLive_" + m_sDevID, 2, sData1, "pgLibLive.RelayNum");

		int iErr = m_Node.ObjectRequest("thisLive_" + m_sDevID, 32, sData, "pgLibLive.VideoStart");
		if (iErr > 0) {
			OutString("pgLibLive.VideoStart: Open live failed. iErr=" + iErr);
			return false;
		}

		// Start play video.
		sData = "(Action){1}(Param){0}";
		iErr = m_Node.ObjectRequest("thisLive_" + m_sDevID, 34, sData, "pgLibLive.VideoPlay");
		OutString("pgLibLive.VideoInit iErr:" + iErr);

		return true;
	}

	private void VideoClean(boolean bPreview) {
		if (m_Node == null) {
			return;
		}

		if (bPreview) {
			if (m_iMode == pgLibLiveMode.Capture) {
				m_Node.ObjectRequest("Prvw", 33, "", "pgLibLive.PrvwStop");
				m_Node.ObjectDelete("Prvw");
			}
		}

		m_Node.ObjectRequest("thisLive_" + m_sDevID, 33, "", "pgLibLive.VideoStop");
		m_Node.ObjectDelete("thisLive_" + m_sDevID);

		OutString("pgLibLive.VideoClean end");
	}
	
	private boolean VideoOption(String sParam) {
		if (m_Node == null) {
			return false;
		}

		if (!m_Node.ObjectAdd("_vTemp", "PG_CLASS_Video", "", 0)) {
			return false;
		}

		String sParamTemp = sParam;
		if (sParamTemp.equals("")) {
			sParamTemp = m_sVideoParam;
		}

		String sBitRate = m_Node.omlGetContent(sParamTemp, "BitRate");
		int iBitRate = ParseInt(sBitRate, 0);
		if (iBitRate != 0) {
			String sValue = "(BitRate){" + iBitRate + "}(FrmRate){0}(KeyFrmRate){0}(LossAlloc){0}";
			String sData = "(Item){5}(Value){" + m_Node.omlEncode(sValue) + "}";
			m_Node.ObjectRequest("_vTemp", 2, sData, "pgLibLive.VideoOption");
		}
		
		String sFrmRate = m_Node.omlGetContent(sParamTemp, "Rate");
		int iFrmRate = ParseInt(sFrmRate, 0);
		if (iFrmRate != 0) {
			String sData = "(Item){4}(Value){" + iFrmRate + "}";
			m_Node.ObjectRequest("_vTemp", 2, sData, "pgLibLive.VideoOption");
		}

		// Set camera facing.
		String sCameraNo = m_Node.omlGetContent(sParamTemp, "CameraNo");
		if (sCameraNo.equals("")) {
			sCameraNo = Integer.valueOf(CameraInfo.CAMERA_FACING_FRONT).toString();
		}
		m_Node.ObjectRequest("_vTemp", 2, "(Item){0}(Value){" + sCameraNo + "}", "");

		m_Node.ObjectDelete("_vTemp");
		return true;
	}
	
	private boolean VideoRedefModeSize(int iMode, int iWidth, int iHeight) {
		if (m_Node == null) {
			return false;
		}

		boolean bRes = false;
		if (m_Node.ObjectAdd("_vTemp", "PG_CLASS_Video", "", 0x0)) {
			String sValue = "(Mode){" + iMode + "}(Width){" + iWidth + "}(Height){" + iHeight + "}";
			String sData = "(Item){12}(Value){" + m_Node.omlEncode(sValue) + "}";
			int iErr = m_Node.ObjectRequest("_vTemp", 2, sData, "");
			if (iErr > 0) {
				OutString("pgLibLive.VideoModeSize: iErr=" + iErr);	
			}
			else {
				bRes = true;
			}
			m_Node.ObjectDelete("_vTemp");
		}

		return bRes;
	}
	
	private boolean AudioInit() {
		if (m_Node == null) {
			return false;
		}

		int iAddFlag = (0x10000 | 0x01);
		int iReliable = ParseInt(m_Node.omlGetContent(m_sAudioParam, "Reliable"), 0);
		if (iReliable != 0) {
			iAddFlag |= 0x10;
		}
		
		String sSpeechSelf = m_Node.omlGetContent(m_sAudioParam, "SpeechSelf");
		if (!sSpeechSelf.equals("")) {
			int iSpeechSelf = ParseInt(sSpeechSelf, 0);
			if (iSpeechSelf == 0) {
				iAddFlag |= 0x20;				
			}
		}

		String sSpeechPeer = m_Node.omlGetContent(m_sAudioParam, "SpeechPeer");
		if (!sSpeechPeer.equals("")) {
			int iSpeechPeer = ParseInt(sSpeechPeer, 0);
			if (iSpeechPeer == 0) {
				iAddFlag |= 0x40;				
			}
		}

		if (!m_Node.ObjectAdd("thisAudio_" + m_sDevID, "PG_CLASS_Audio", m_sGroupName, iAddFlag)) {
			OutString("pgLibLive.AudioInit: Add 'thisAudio' failed.");
			return false;
		}

		int iErr = m_Node.ObjectRequest("thisAudio_" + m_sDevID, 32,
			"(Code){1}(Mode){0}", "pgLibLive.AudioStart");
		if (iErr > 0) {
			OutString("pgLibLive.AudioInit: Open audio failed. iErr=" + iErr);
			return false;
		}

		return true;
	}

	private void AudioClean() {
		if (m_Node == null) {
			return;
		}

		m_Node.ObjectRequest("thisAudio_" + m_sDevID, 33, "", "pgLibLive.AudioStop");
		m_Node.ObjectDelete("thisAudio_" + m_sDevID);
	}


	///------------------------------------------------------------------------
	// Callback process functions.

	private void SelfSync(String sData, String sPeer) {
		if (m_Node == null) {
			return;
		}

		String sAct = m_Node.omlGetContent(sData, "Action");
		if (!sAct.equals("1")) {
			if (sPeer.equals(m_sObjSvr)) {
				NodeRelogin(10);
			}
		}
	}

	private int SelfMessage(String sData, String sPeer) {
		if (m_Node == null) {
			return 0;
		}

		String sCmd = "";
		String sParam = "";
		int iInd = sData.indexOf('?');
		if (iInd > 0) {
			sCmd = sData.substring(0, iInd);
			sParam = sData.substring(iInd + 1);
		}
		else {
			sParam = sData;
		}
		
		if (sCmd.equals("Active")) {
			if (m_bStarted) {
				if (m_iMode == pgLibLiveMode.Capture) {
					RenderInfo info = RenderSearch(sPeer);
					if (info != null) {
						info.iStamp = m_iActiveStamp;
					}
				}
				else {
					m_Node.ObjectRequest(m_sObjCap, 36, "Active?", "pgLibLive.MessageSend");
				}
			}
			return 0;
		}
		else if (sCmd.equals("Msg")) {
			EventProc("Message", sParam, sPeer);
			return 0;
		}
		else if (sCmd.equals("FrmPull")) {
			if (m_bApiVideoStart) {
				m_Node.ObjectRequest("thisLive_" + m_sDevID, 34,
					"(Action){4}(Param){1}", "pgLibLive.FrmPull");
			}
			return 0;
		}

		return 0;
	}
	
	private int ServerMessage(String sData, String sPeer) {
		if (m_Node == null) {
			return 0;
		}
		
		String sCmd = "";
		String sParam = "";
		int iInd = sData.indexOf('?');
		if (iInd > 0) {
			sCmd = sData.substring(0, iInd);
			sParam = sData.substring(iInd + 1);
		}
		else {
			sParam = sData;
		}

		if (sCmd.equals("UserExtend")) {
			EventProc("SvrNotify", sParam, sPeer);
		}
		else if (sCmd.equals("Restart")) {
			if (sParam.indexOf("redirect=1") >= 0) {
				NodeRedirectReset(3);
			}
			else {
				NodeRelogin(3);
			}
		}
		
		return 0;
	}
	
	private void ServerError(String sData, String sPeer) {
		String sMeth = m_Node.omlGetContent(sData, "Meth");
		if (sMeth.equals("32")) {
			String sError = m_Node.omlGetContent(sData, "Error");
			if (sError.equals("10")) {
				NodeRelogin(3);
			}
			else if (sError.equals("11")
				|| sError.equals("12")
				|| sError.equals("14"))
			{
				NodeRedirectReset(0);
			}
		}
	}

	private void ServerRelogin(String sData, String sPeer) {
		String sError = m_Node.omlGetContent(sData, "ErrCode");
		if (sError.equals("0")) {
			String sParam = m_Node.omlGetContent(sData, "Param");
			String sRedirect = m_Node.omlGetEle(sParam, "Redirect.", 10, 0);
			if (!sRedirect.equals("")) {
				NodeRedirect(sRedirect);
				return;
			}
			
			m_bLogin = true;
			EventProc("Login", "0", m_sObjSvr);
		}
	}

	private void CapPeerSync() {
		if (m_iMode != pgLibLiveMode.Render) {
			return;
		}
		m_bCapPeerCheckTimer = false;
		OutString("pgLibLive.CapPeerSync: sObjCap = " + m_sObjCap);
	}

	private int GroupUpdate(String sData) {
		if (m_Node == null) {
			return 0;
		}

		String sAct = m_Node.omlGetContent(sData, "Action");
		String sPeerList = m_Node.omlGetEle(sData, "PeerList.", 256, 0);

		int iInd = 0;
		while (true) {
			String sEle = m_Node.omlGetEle(sPeerList, "", 1, iInd);
			if (sEle.equals("")) {
				break;
			}

			String sPeer = m_Node.omlGetName(sEle, "");
			if (sPeer.indexOf("_CAP_") != 0 && sPeer.indexOf("_LFS_") != 0) {
				if (sAct.equals("1")) {
					if (!m_Node.ObjectAdd("File_" + sPeer, "PG_CLASS_File", sPeer, 0x10000)) {
						OutString("add File_" + sPeer + " defeated!");
					}
					RenderAdd(sPeer);
				}
				else {
					m_Node.ObjectDelete("File_" + sPeer);
					RenderDelete(sPeer);
				}
			}
			else if (m_iMode == pgLibLiveMode.Render && sPeer.equals(m_sObjCap)) {
				if (sAct.equals("1")) {
					EventProc("Connect", "", "");
				}
				else {
					CapDisconnect();
					EventProc("Disconnect", "", "");
				}
			}

			iInd++;
		}

		return 0;
	}
	
	private void VideoStatus(String sData) {
		if (m_Node == null) {
			return;
		}

		if (!m_bApiVideoStart) {
			return;
		}

		String sBitRate = m_Node.omlGetContent(sData, "BitRate");
		String sFrmRate = m_Node.omlGetContent(sData, "FrmRate");
		String sFrmPlay = m_Node.omlGetContent(sData, "FrmPlay");
		String sDataTemp = "bitrate=" + sBitRate + "&frmrate=" + sFrmRate + "&frmplay=" + sFrmPlay;

		EventProc("VideoStatus", sDataTemp, "");
	}
	
	private void VideoFrameStat(String sData, String sPeer) {
		if (m_Node == null) {
			return;
		}

		if (!m_bApiVideoStart) {
			return;
		}

		String sPeerTemp = m_Node.omlGetContent(sData, "Peer");
		String sFrmTotal = m_Node.omlGetContent(sData, "Total");
		String sFrmDrop = m_Node.omlGetContent(sData, "Drop");

		String sDataTemp = "total=" + sFrmTotal + "&drop=" + sFrmDrop;
		EventProc("VideoFrameStat", sDataTemp, sPeerTemp);
	}
	
	private void VideoCameraReply(String sData) {
		if (m_Node == null) {
			return;
		}
		if (!m_bApiVideoStart) {
			return;
		}

		String sPath = m_Node.omlGetContent(sData, "Path");
		EventProc("VideoCamera", sPath, "");
	}
	
	private void CapOffline() {
		EventProc("Offline", "", "");
		CapPeerStatic();
		CapDisconnect();
	}

	private void CapDisconnect() {
		if (!m_bCapPeerCheckTimer) {
			if (TimerStart("(Act){CapPeerCheck}", 3, false) >= 0) {
				m_bCapPeerCheckTimer = true;
			}
		}
	}
	
	private void LanScanResult(String sData) {
		if (m_Node == null) {
			return;
		}

		m_sLanScanRes = "";

		int iInd = 0;
		while (true) {
			String sEle = m_Node.omlGetEle(sData, "PeerList.", 1, iInd);
			if (sEle.equals("")) {
				break;
			}
			
			String sPeer = m_Node.omlGetName(sEle, "");
			int iPos = sPeer.indexOf("_CAP_");
			if (iPos == 0) {
				String sAddr = m_Node.omlGetContent(sEle, ".Addr");
				if (m_bApiLanScan) {
					String sID = sPeer.substring(5);
					String sDataTemp = "id=" + sID + "&addr=" + sAddr;
					EventProc("LanScanResult", sDataTemp, "");
				}
				m_sLanScanRes += "(" + sPeer + "){" + sAddr + "}";
			}

			iInd++;
		}

		if (!m_bLogin) {
			CapPeerStatic();
		}

		m_bApiLanScan = false;
	}

	private int FileReq(int iMethod, String sData, String sPeer, int uHandle) {
		if (m_Node == null) {
			return PG_ERR_BadStatus;
		}

		if (m_iFileFlag != 0 && m_iMode == pgLibLiveMode.Render) {
			return PG_ERR_BadStatus;
		}

		handleList.put(sPeer, Integer.valueOf(uHandle).toString());

		String sFile = m_Node.omlGetContent(sData, "PeerPath");
		String sParam = "peerpath=" + sFile;

		if (iMethod == 32) {
			EventProc("FilePutRequest", sParam, sPeer);
		}
		else if (iMethod == 33) {
			EventProc("FileGetRequest", sParam, sPeer);
		}

		m_iFileFlag = 1;
		return -1; // Async reply
	}

	private int FileStatus(String sData, String sPeer) {
		if (m_Node == null) {
			return 0;
		}

		int uStatus = ParseInt(m_Node.omlGetContent(sData, "Status"), 0);
		if (uStatus != 3) {
			String sPath = m_Node.omlGetContent(sData, "Path");
			String sReqSize = m_Node.omlGetContent(sData, "ReqSize");
			String sCurSize = m_Node.omlGetContent(sData, "CurSize");

			String sParam = "path=" + sPath + "&total=" + sReqSize + "&position=" + sCurSize;
			EventProc("FileProgress", sParam, sPeer);
		}
		else { // Stop
			m_iFileFlag = 0;
			String sPath = m_Node.omlGetContent(sData, "Path");
			String sReqSize = m_Node.omlGetContent(sData, "ReqSize");
			String sCurSize = m_Node.omlGetContent(sData, "CurSize");

			String sParam = "path=" + sPath + "&total=" + sReqSize + "&position=" + sCurSize;
			EventProc("FileProgress", sParam, sPeer);

			if (ParseInt(sReqSize, 0) > 0 && ParseInt(sCurSize, 0) >= ParseInt(sReqSize, 0)) {
				EventProc("FileFinish", sParam, sPeer);
			}
			else {
				EventProc("FileAbort", sParam, sPeer);
			}
		}

		return 0;
	}

	private void ForwardAllocReply(int iErr) {
		EventProc("ForwardAllocReply", iErr + "", "");
	}

	private void ForwardFreeReply(int iErr) {
		EventProc("ForwardFreeReply", iErr + "", "");
	}

	private void SvrReply(int iErr, String sData) {
		if (iErr != 0) {
			EventProc("SvrReplyError", iErr + "", "");
		}
		else {
			EventProc("SvrReply", sData, "");			
		}
	}

	
	///------------------------------------------------------------------------
	// Node callback functions.
	private int NodeOnExtRequest(String sObj, int iMeth, String sData, int iHandle, String sPeer) {
		if (m_Node == null) {
			return 0;
		}

		if (m_iMode == pgLibLiveMode.Render) {
			Log.d("render", "Render.NodeOnExtRequest: " + sObj + ", " + iMeth
				+ ", " + sData + ", " + iHandle + ", " + sPeer);
		}
		
		if (m_iMode == pgLibLiveMode.Capture) {
			Log.d("capture", "Capture.NodeOnExtRequest: " + sObj + ", " + iMeth
				+ ", " + sData + ", " + iHandle + ", " + sPeer);
		}

		if (sObj.indexOf("File_") == 0) {
			if (iMeth == 32) { // put file request
				String sPeer1 = sObj.split("File_")[1];
				return FileReq(32, sData, sPeer1, iHandle);
			}
			if (iMeth == 33) { // get file request
				String sPeer1 = sObj.split("File_")[1];
				return FileReq(33, sData, sPeer1, iHandle);
			}
			if (iMeth == 34) { // File transfer status report.
				String sPeer1 = "";
				if (m_iMode == pgLibLiveMode.Render) {
					sPeer1 = m_sObjCap;
				}
				else {
					sPeer1 = sObj.split("File_")[1];
				}
				FileStatus(sData, sPeer1);
				return 0;
			}
			if (iMeth == 35) { // Cancel file request
				String sPeer1 = sObj.split("File_")[1];
				doFileCancel(sPeer1);
				return 0;
			}
			return 0;
		}

		if (m_iMode == pgLibLiveMode.Capture) {
			if (sObj.equals(m_sUser)) {
				if (iMeth == 36) {
					if (sData.indexOf("CMD?") == 0) {
						String sParam = sData.split("CMD?")[1];
						String sMethod = m_Node.omlGetContent(sParam, "method");
						int iErr = Integer.valueOf(m_Node.omlGetContent(sParam, "iErr"));
						if (sMethod.equals("DeleteGroupReply")) {
							ForwardFreeReply(iErr);
						}
					}
				}
			}
		}

		if (sObj.equals("thisLive_" + m_sDevID)) {
			if (iMeth == 35) {
				VideoStatus(sData);
			}
			else if (iMeth == 39) {
				VideoFrameStat(sData, sPeer);
			}
			return 0;
		}

		if (sObj.equals(m_sGroupName)) {
			if (iMeth == 33) {
				return GroupUpdate(sData);
			}
			return 0;
		}

		if (sObj.equals("thisData_" + m_sDevID)) {
			if (iMeth == 32) {
				EventProc("Notify", sData, sPeer);
			}
			else if (iMeth == 0) {
				if (m_iMode == pgLibLiveMode.Render && sPeer.equals(m_sObjCap)) {
					String sAct = m_Node.omlGetContent(sData, "Action");
					if (!sAct.equals("1")) {
						CapDisconnect();
						EventProc("Disconnect", "", "");
					}
				}
				return 0;
			}
			return 0;
		}
		
		if (sObj.equals(m_sObjSelf)) {
			if (iMeth == 36) {
				if (sPeer.equals(m_sObjSvr)) {
					return ServerMessage(sData, sPeer);
				}
				else {
					return SelfMessage(sData, sPeer);
				}				
			}
			else if (iMeth == 0) {
				SelfSync(sData, sPeer);
			}
			return 0;
		}

		if (sObj.equals(m_sObjSvr)) {
			if (iMeth == 0) {
				String sAct = m_Node.omlGetContent(sData, "Action");
				if (!sAct.equals("1") && !m_sObjSvr.equals("")) {
					NodeRelogin(10);
				}
			}
			else if (iMeth == 1) {
				ServerError(sData, sPeer);
			}
			else if (iMeth == 46) {
				ServerRelogin(sData, sPeer);
			}
			return 0;
		}

		if (sObj.equals(m_sObjCap)) {
			if (m_iMode == pgLibLiveMode.Render) {
				if (iMeth == 0) {
					String sAct = m_Node.omlGetContent(sData, "Action");
					if (sAct.equals("1")) {
						CapPeerSync();
					}
				}
				else if (iMeth == 1) {
					String sMeth = m_Node.omlGetContent(sData, "Meth");
					if (sMeth.equals("34")) {
						CapOffline();
					}
				}
			}
			return 0;
		}

		return 0;
	}

	private int NodeOnReply(String sObj, int iErr, String sData, String sParam) {
		if (m_Node == null) {
			return 1;
		}

		if (m_iMode == pgLibLiveMode.Render) {
			Log.d("render", "Render.NodeOnReply: " + sObj + ", " + iErr + ", " + sData + ", " + sParam);
		}

		if (m_iMode == pgLibLiveMode.Capture) {
			Log.d("capture", "Capture.NodeOnReply: " + sObj + ", " + iErr + ", " + sData + ", " + sParam);
		}

		if (sObj.equals(m_sObjSvr)) {
			if (sParam.equals("pgLibLive.NodeLogin")) {
				return NodeLoginReply(iErr, sData);
			}
			else if (sParam.equals("pgLibLive.LanScan")) {
				LanScanResult(sData);
			}
			else if (sParam.equals("pgLibLive.ForwardAlloc")) {
				ForwardAllocReply(iErr);
			}
			else if (sParam.equals("pgLibLive.ForwardFree")) {
				ForwardFreeReply(iErr);
			}
			else if (sParam.equals("pgLibLive.SvrRequest")) {
				SvrReply(iErr, sData);
			}
			return 1;
		}

		if (sObj.indexOf("File_") == 0) {
			if (sParam.equals("pgLibLive.FileGetRequest")) { // Cancel file
				if (iErr != 0) {
					m_iFileFlag = 0;
					EventProc("FileReject", Integer.valueOf(iErr).toString(), sObj);
					return 0;
				}
				else {
					m_iFileFlag = 1;
					EventProc("FileAccept", Integer.valueOf(iErr).toString(), sObj);
					return 0;
				}
			}
			else if (sParam.equals("pgLibLive.FilePutRequest")) { // Cancel file
				if (iErr != 0) {
					m_iFileFlag = 0;
					EventProc("FileReject", Integer.valueOf(iErr).toString(), sObj);
					return 0;
				}
				else {
					m_iFileFlag = 1;
					EventProc("FileAccept", Integer.valueOf(iErr).toString(), sObj);
					return 0;
				}
			}
			
			return 1;
		}
		
		String sClass = m_Node.ObjectGetClass(sObj);
		if (sClass.equals("PG_CLASS_Live")) {
			if (sParam.equals("pgLibLive.VideoCamera")) {
				VideoCameraReply(sData);
			}
			
			return 1;
		}

		return 1;
	}


	///------------------------------------------------------------------------
	// Timer handler

	// Timer list.
	class Item {
		public String sParam = "";
		
		public int iCookie = 0;
		public boolean bRepeat = false;
		public Timer timer = null;
		public pgTimerTask timerTask = null;

		public Item(String sParam1) {
			sParam = sParam1;
		}
	}
	
	private Handler m_TimerHandler = null;
	private ArrayList<Item> s_TimeList = new ArrayList<Item>();

	// Timer class.
	class pgTimerTask extends TimerTask {
		int m_iTimeID = -1;

		public pgTimerTask(int iTimerID) {
			super();
			m_iTimeID = iTimerID;
		}

		public void run() {
			try {
				if (m_TimerHandler != null) {
					Message oMsg = m_TimerHandler.obtainMessage(0, Integer.valueOf(m_iTimeID));
					m_TimerHandler.sendMessage(oMsg);
				}
			}
			catch (Exception ex) {
				OutString("pgLibLive.pgTimerTask.run, ex=" + ex.toString());
			}
		}
	}

	// Create Timer message handler.
	private boolean TimerInit() {
		try {
			m_TimerHandler = new Handler() {  
				@Override  
				public void handleMessage(Message msg) {  
					
					int iTimerID = ((Integer)msg.obj).intValue();
					int iCookie = iTimerID & 0xffff;
					int iItem = (iTimerID >> 16) & 0xffff;
		
					if (iItem >= s_TimeList.size()
						|| s_TimeList.get(iItem).iCookie != iCookie)
					{
						return;
					}
					
					// Timer callback.
					Item item = s_TimeList.get(iItem);
					TimerProc(item.sParam); 

					if (item.bRepeat) {
						return;
					}
		
					if (item.timer != null) {
						item.timer.cancel();
					}
		
					item.sParam = "";
					item.timerTask = null;
					item.timer = null;
					item.iCookie = 0;
					item.bRepeat = false;
				}
			};

			return true;
		}
		catch (Exception ex) {
			OutString("pgLibLive.TimerInit: ex=" + ex.toString());
			return false;
		}
	}
	
	private void TimerClean() {
		for (int i = 0; i < s_TimeList.size(); i++) {
			try {
				if (s_TimeList.get(i).timer != null) {
					s_TimeList.get(i).timer.cancel();
				}
	
				s_TimeList.get(i).sParam = "";
				s_TimeList.get(i).timerTask = null;
				s_TimeList.get(i).timer = null;
				s_TimeList.get(i).iCookie = 0;
			}
			catch (Exception ex) {
				OutString("pgLibLive.TimerClean, ex=" + ex.toString());
			}
		}
		m_TimerHandler = null;
	}

	private int TimerStart(String sParam, int iTimeout, boolean bRepeat) {
		
		try {
			int iItem = -1;
			for (int i = 0; i < s_TimeList.size(); i++) {
				if (s_TimeList.get(i).timer == null) {
					iItem = i;
					break;
				}
			}
			if (iItem < 0) {
				s_TimeList.add(new Item(sParam));
				iItem = s_TimeList.size() - 1;
			}

			int iCookie = (m_Random.nextInt() & 0xffff);
			int iTimerID = (((iItem << 16) & 0xffff0000) | iCookie);

			Timer timer = new Timer();
			pgTimerTask timerTask = new pgTimerTask(iTimerID);

			Item item = s_TimeList.get(iItem);
			item.sParam = sParam;

			item.timer = timer;
			item.timerTask = timerTask;
			item.iCookie = iCookie;
			item.bRepeat = bRepeat;

			if (bRepeat) {
				timer.schedule(timerTask, (iTimeout * 1000), (iTimeout * 1000));
			}
			else {
				timer.schedule(timerTask, (iTimeout * 1000));
			}
			
			return iTimerID;	
		}
		catch (Exception ex) {
			OutString("pgLibLive.Add, ex=" + ex.toString());
			return -1;
		}
	}
	
	private void TimerStop(int iTimerID) {

		int iCookie = iTimerID & 0xffff;
		int iItem = (iTimerID >> 16) & 0xffff;
		if (iItem >= s_TimeList.size()
			|| s_TimeList.get(iItem).iCookie != iCookie)
		{
			return;
		}

		try {
			if (s_TimeList.get(iItem).timer != null) {
				s_TimeList.get(iItem).timer.cancel();
			}

			s_TimeList.get(iItem).sParam = "";
			s_TimeList.get(iItem).timerTask = null;
			s_TimeList.get(iItem).timer = null;
			s_TimeList.get(iItem).iCookie = 0;
		}
		catch (Exception ex) {
			OutString("pgLibLive.TimerStop, ex=" + ex.toString());
		}
	}
}
