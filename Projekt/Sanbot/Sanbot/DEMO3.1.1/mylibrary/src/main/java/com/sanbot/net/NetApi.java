package com.sanbot.net;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by QH on 2017/1/6.
 */

public class NetApi {
    /**单聊企业单聊**/
    public static final int CHAT_FUNCTION_TYPE_SINGLE = 1;
    /**群聊天包括企业部门聊天**/
    public static final int CHAT_FUNCTION_TYPE_GROUP = 2;
    /**附近的人聊天**/
    public static final int CHAT_FUNCTION_TYPE_NEARBY = 3;
    /**一般聊天**/
    public static final int CHAT_TYPE_NORMAL = 0;
    /**企业单聊**/
    public static final int CHAT_TYPE_COMPANY_SINGLE = 1;
    /**附近的人 暂时不有使用**/
    public static final int CHAT_TYPE_NEARBY = 2;
    /**公司聊天与公司成员、部门、公司群相关 param1 部门ID param2 企业ID**/
    public static final int CHAT_TYPE_COMPANY = 3;

    private ShowRecvListener rListener;

    private ShowVideoListener videoListener;

    public static NetApi netApi;

    public synchronized static NetApi getInstance() {
        if (netApi == null)
            netApi = new NetApi();
        return netApi;
    }

    public ShowVideoListener getVideoListener() {
        return videoListener;
    }

    public void setVideoListener(ShowVideoListener videoListener) {
        this.videoListener = videoListener;
    }

    /**
     * 初始化网络库 只执行一次
     * @param app_id
     * @param domain 服务器地址
     * @param port  服务器端口
     * @param version APP版本号
     * @return 0
     */
    public native int initLib(Context context, int app_id, String deviceId, String domain, int port, String version);

    /**
     * 初始化网络库 只执行一次
     * @return
     */
    public native int initQHLib(Context context);

    /**
     * 反初始化
     * @return 0
     */
    public native int cleanupLib();

    /**
     * 判断网络库是否初始化
     * @return
     */
    public native boolean isInitLib();

    /**
     * 是否使用加密
     * @param isEncryption 0：不加密 1：加密
     * @return
     */
    public native int setEncryption(int isEncryption);

    /**
     * 设置服务器信息
     *
     * @param smsServer  消息服务器地址
     * @param smsPort    消息服务器端口
     * @param p2pServer  P2P服务器地址
     * @param p2pPort    P2P服务器端口
     * @param userid     用户UID
     */
    public native void setServerInfo(String smsServer, int smsPort, String p2pServer, int p2pPort, int userid);

    /**
     * 获取服务器信息
     *
     * @return 服务器信息 失败返回null
     */
    public native ServerInfo getServerInfo();

    /**
     * 注册帐号
     * @param account
     * account->account 帐号
     * account->password 密码
     * account->tel 手机号
     * account->identify 验证码
     * account->areaCode 国家号
     * @return 返回值： 0-成功，其他-错误码
     */
    public native int onRegistAccount(AccountRegister account);

    /**
     * 使用邮箱注册帐号
     * @param account
     * @return
     */
    public native int onRegistAccountByEmail(EmailAccountRegister account);

    /**
     * 获取注册手机验证码
     * @param account
     * account->account qlink帐号
     * account->tel 手机号码
     * @return 返回值： 0-成功，其他-错误码
     */
    public native int onGetRegistIdentify(AccountIdentify account);
	
	/**
     * 判断注册验证码是否正确
     * @param identifyInfo
     * identifyInfo->identify 验证码
     * identifyInfo->tel 手机号码
     * identifyInfo->areaCode 国家号
     * @return 返回值： 0-成功，其他-错误码
     */
	public native int onCheckRegistIdentify(IdentifyInfo identifyInfo);

    /**
     * 检查帐号是否存在
     * @param account
     * account->accountType　帐号类型 qlink_id, email, tel
     * account->aaccount　帐号
     * @return  返回值： 0-未注册 1-已注册 ，其他-错误码
     */
    public native int onCheckAccount(BaseAccount account);

    /**
     * 获取重置密码验证码
     * @param tel
     * @return 返回值： 0-成功，其他-错误码
     */
    public native int onGetResetPwdPhoneidentify(String tel);

    /**
     * 重置密码
     * @param info
     * info->type 帐号类型
     * info->account 帐号
     * info->identify 验证码
     * info->password 新密码
     * @return 返回值： 0-成功，其他-错误码
     */
    public native int onResetPwd(AccountPassword info);

    /**
     *通过邮箱重置密码
     * @param info
     * info->account qlink帐号
     * info->email 注册邮箱地址
     * info->lang 语言 1为英文 0为中文。
     * @return 返回值： 0-成功，其他-错误码
     */
    public native int onResetPwdByEmail(EmailPassword info);

    /**
     * 登录到云服务器
     * @param loginAccount
     * loginAccount->type 帐号类型 tel qlink_id email
     * loginAccount->account 帐号信息
     * loginAccount->password 帐号密码
     * @return 直接返回是否登录成功 非0为错误码
     *
     */
    public native int onLogin(LoginAccount loginAccount);

    /**
     * 登出
     * @return 返回值： 0-成功，其他-错误码
     */
    public native int onLogout();

    /**
     * 停止登录
     * @return 返回值： 0-成功，其他-错误码
     */
    public native int stopLogin();

    /**
     *  描述：获取在线状态。
     *  阻塞方式：非阻塞，立即返回。
     *  返回值：返回0表示设备不在线，返回1表示设备在线
     */
    public native int getLoginStatus();

    /**
     * 获取登录验证码
     * @param tel 手机号码
     * @return 返回值： 0-成功，其他-错误码
     */
    public native int onGetLoginIdentify(String tel);

    /**
     * 通过手机验证码登录
     * @param identifyInfo 验证码
     * @return 返回值： 0-成功，其他-错误码
     */
    public native int onLoginByIdentify(IdentifyInfo identifyInfo);

    /**
     * 获取登录用户UID
     * @return 返回UID, 小于等于0为失败
     */
    public native int onGetUID();

    /**
     * 获取好友UID列表
     * @param seq
     * @return  返回值：0-成功，其他-错误码
     *  回调命令： NetInfo.QHC_CMD_GET_FRIEND_VERSION_RSP  数据:ArrayList<FriendVersion>
     */
    public native int onGetFriendUIDs(long seq);

    /**
     * 获取好友备注信息
     * @param uids 用户UID
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     *  回调命令： NetInfo.QHC_CMD_GET_FRIEND_REMARKS_INFO_QUERY_RSP 数据:ArrayList<FriendRemark>
     */
    public native int onGetFriendRemarks(List<Integer> uids, long seq);

    /**
     * 获取好友基本信息
     * @param uids 用户UID
     * @param seq
     * @return 返回值 0-成功，其他-错误码
     *  回调命令： NetInfo.QHC_CMD_GET_FRIEND_BASE_INFO_QUERY_RSP 数据:ArrayList<BaseInfo>
     */
    public native int onGetFriendBaseInfos(List<Integer> uids, long seq);

    /**
     * 获取登录用户的基本信息
     * @return 0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_GET_SINGLE_FRIEND_BASE_INFO_QUERY_RSP 116 数据:BaseInfo
     */
    public native int onGetMyBaseInfo();

    /**
     * 请求添加对方为好友
     * @param request 对方UID
     * request->uid 对方UID
     * request->desc 备注内容
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     *  回调命令： NetInfo.QHC_CMD_ADD_ASK_RSP
     */
    public native int onRequestBeFriend(RequestBeFriend request, long seq);

    /**
     * 回应请求添加为好友
     * @param  response
     * response->ask_uid　添加方user_id
     * response->dev_uid 设备UID　
     * response->type　回应类型　 应答类型，0-拒绝，1-同意。
     * response->msg_id　消息ID
     * response->permission　权限
     * @param seq　
     * @return 返回值：0-成功，其他-错误码
     *  回调命令： NetInfo.QHC_CMD_ADD_ANSWER_RSP
     */
    public native int onResponseBeFriend(ResponseBeFriend response, long seq);

    /**
     * 删除好友关系
     * @param uid 好友UID
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     *  回调命令： NetInfo.QHC_CMD_DELETE_FIREND_RSP
     */
    public native int onDeleteFriend(int uid, long seq);

    /**
     * 更新好友备注信息
     * @param remark 备注信息
     * remark->uid 用户UID
     * remark->remark 备注信息
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     *  回调命令： NetInfo.QHC_CMD_SET_FRIEND_REMARKS_RSP
     */
    public native int onSetRemark(RemarkInfo remark, long seq);

    /**
     * 通过帐号获取UID
     * @param account　帐号
     *  account->accountType　帐号类型 qlink_id, email, tel
     *  account->aaccount　帐号
     * @param seq　
     * @return 返回值：0-成功，其他-错误码
     *  回调命令： NetInfo.QHC_CMD_UID_QUERY_RSP 数据：Integer
     */
    public native int onGetUIDByAccount(BaseAccount account, long seq);

    /**
     * 通过帐号获取UID
     * @param account　帐号
     *  account->accountType　帐号类型 qlink_id, email, tel
     *  account->aaccount　帐号
     * @return 返回值：0
     */
    public native GetUID onGetAccountUId(BaseAccount account);

    /**
     * 发送信息
     * @param uid 对方UID
     * @param data 发送内容
     * @param len 发送内容长度
     * @param type 信息类型
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     *  回调命令： NetInfo.QHC_CMD_SEND_CHAT_MSG_RSP
     */
    public native int onSendMsg(int uid, byte[] data, int len, int type, long seq);

    /**
     * 给对方发送控制内容
     * @param params
     * params->uid 对方UID
     * params->params 发送内容
     * params->type 内容类型
     * params->sendType 内容类型 0 有回调 非0无回调
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     *  回调命令： NetInfo.QHC_CMD_CTRL_MSG_RSP 数据：SettingParams
     */
    public native int onSendSettingCMD(Settings params, long seq);

    /**
     * 打开视频
     * @param video
     * video->dev_id 设备UID
     * video->channel 视频通道 0
     * video->type 码流类型 0
     * video->stream_contents 0-音视频、1-视频、2-音频
     * @return 返回值： 0-失败，会话ID
     */
    public native VideoHandle onOpenVideo(VideoInfo video);

    /**
     * 关闭视频
     * @param handle　会话ID　onOpenVideo返回值
     * @return 返回值： 0-成功，其他-错误码
     */
    public native int onCloseVideo(long handle);

    /**
     * 设置视频流类型
     * @param handle 会话ID　onOpenVideo返回值
     * @param stream 0-音视频、1-视频、2-音频。
     * @return
     */
    public native int onChangeVideoStream(long handle, int stream);

    /**
     * 开启录像
     * @param filename
     * @return
     */
    public native int startRecord(String filename);

    /**
     * 关闭录像
     * @return
     */
    public native int stopRecord();

    /**
     * 回放录像
     * @param position 开始时间
     * @param filename 文件名包括路径
     * @return
     */
    public native int startPlayRecordFile(int position, String filename);

    /**
     * 停止回放录像
     * @return
     */
    public native int stopPlayRecordFile();

    /**
     * 暂停录像回放
     * @param pause  1 暂停 0正常
     * @return
     */
    public native int pausePlayRecordFile(int pause);

    /**
     * 视频聊天时发送心跳消息
     * @param seq
     * @return 返回值： 0-成功，其他-错误码
     */
    public native int sendVideoTalkGreeting(long seq);

    /**
     * 查询帐号信息
     * @param type 字段类型 alias/sex/birthday/height/weight/mail/question1/question2/question3/url
     * @param seq
     * @return   返回值： 0-成功，其他-错误码
     *  回调命令： NetInfo.QHC_CMD_ACCOUNT_QUERY_RSP  数据：AccountInfo
     */
    public native int queryAccountInfo(String type, long seq);

    /**
     * 设置帐号信息
     * @param info
     *  info->type 字段类型 alias/sex/birthday/height/weight/mail/question1/question2/question3/url
     *  info->value 设置内容
     * @param seq
     * @return 返回值： 0-成功，其他-错误码
     *  回调命令： NetInfo.QHC_CMD_ACCOUNT_MODIFY_RSP
     */
    public native int setAccountInfo(SetAccountInfo info, long seq);

    /**
     * 修改密码
     * @param pwd
     *  pwd->pwdaccountType qlink_id, email, tel
     *  wd->account 帐号
     *  wd->old_pwd 旧密码
     *  wd->new_pwd 新密码
     * @param seq
     * @return 返回值： 0-成功，其他-错误码
     *  回调命令： NetInfo.QHC_CMD_MODIFY_PASSWORD_RSP　
     */
    public native int modifyPassword(AccountModifyPwd pwd, long seq);

    /**
     * 获取设备好友列表
     * @param dev_uid 设备UID
     * @param seq
     * @return 返回值： 0-成功，其他-错误码
     *  回调命令： NetInfo.QHC_CMD_GET_DEV_FRIEND_CNT_RSP 数据：List<Integer>
     */
    public native int getDevFriendList(int dev_uid, long seq);

    /**
     * 获取设备好友基本信息
     * @param dev_uid 设备UID
     * @param uids 好友UID_LIST
     * @param seq
     * @return  返回值： 0-成功，其他-错误码
     *  回调命令： NetInfo.QHC_CMD_GET_DEV_FINFO_RSP 数据：List<DevFriendInfo>
     */
    public native int getDevFriendsBaseInfo(int dev_uid, List<Integer> uids, long seq);

    /**
     * 删除设备好友
     * @param dev_uid 设备UID
     * @param friend_uid 设备好友UID
     * @param seq
     * @return 返回值： 0-成功，其他-错误码
     *  回调命令： NetInfo.QHC_CMD_DEL_DEV_FRIEND_RSP
     */
    public native int delDevFriend(int dev_uid, int friend_uid, long seq);

    /**
     * 修改设备好友备注
     * @param dev_uid 设备UID
     * @param friend_uid 设备好友UID
     * @param remark 设备好友备注
     * @param seq
     * @return 返回值： 0-成功，其他-错误码
     *  回调命令： NetInfo.QHC_CMD_MODIFY_DEV_FREMARKS
     */
    public native int modifyDevFriendRemark(int dev_uid, int friend_uid, String remark, long seq);

    /**
     * w修改设备好友权限
     * @param dev_uid 设备UID
     * @param friend_uid 设备好友UID
     * @param permission 设备好友权限
     * @param seq
     * @return 返回值： 0-成功，其他-错误码
     *  回调命令： NetInfo.QHC_CMD_MODIFY_DEV_FPERMISSION
     */
    public native int modifyDevFriendPermission(FriendPermission friendPermission, long seq);

    /**
     * 上传文件
     * @param dst_uid 目标用户UID
     * @param fileName 文件名称
     * @param fileType 文件类型
     * @param fileSize 文件大小
     * @param md5  文件MD5
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     *  回调命令： NetInfo.QHC_CMD_UPLOAD_FILE_RSP 数据：UploadFileResultInfo
     */
    public native int onUploadFiles(UploadFile uploadFile, long seq);

    /**
     * 文件上传完成
     * @param md5 文件内容32位md5码。
     * @param fileType 文件类型。0-聊天图片，1-头像图片，10个人文件
     * @param fileSize  文件大小
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     *  回调命令： NetInfo.QHC_CMD_FINISH_UPLOAD_FILE_RSP 数据：UploadFileFihishiInfo
     */
    public native int onUploadFileFinish(UploadFileFinish uploadFileFinish,  long seq);

    /**
     * 下载文件
     * @param file_id 文件ID
     * @param file_type　文件类型。0-聊天图片，1-头像图片，10个人文件
     * @param thumbnails　 下载图片时,thumbnails=1表示缩略图 thumbnails=0表示下载原图。
     * @param msg_req　
     * @return 返回值：0-成功，其他-错误码
     *  回调命令： NetInfo.QHC_CMD_DOWN_FILE_RSP 数据：DownloadFileResultInfo
     */
    public native int onDownloadFiles(DownloadFile downloadFile, long msg_req);

    /**
     * 完成下载文件
     * @param downloadFileFinish
     * @param msg_req
     * @return 返回值：0-成功，其他-错误码
     *  回调命令： NetInfo.QHC_CMD_FINISH_DOWN_FILE_RSP 数据：
     */
    public native int onDownloadFilesFinish(DownloadFileFinish downloadFileFinish, long msg_req);

    /**
     * 获取转移管理员验证码
     * @param request
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_GET_MODIFY_MGR_RSP
     */
    public native int getModifyMgrIdentify(DevModifyRequet request, long seq);

    /**
     * 转移管理员
     * @param request
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_MODIFY_MGR_RSP
     */
    public native int changeDevAdmin(ChangeDevAdmin request, long seq);
	
	/**
     * 判断是否是管理员
     * @param admin
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QLINK_COMM_CHECK_MGR_RSP
     */
	public native int checkDevAdmin(CheckDevAdmin admin, long seq);

    /**
     * 获取好友版本号
     * @param uid
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_GET_CLIENT_VERSION_RSP VersionInfo
     */
    public native int getUserVersion(int uid, long seq);

    /**
     * 上传点击统计数据
     *
     * @param infos   点击事件 条数上限每次100条
     * @param version
     * @param system
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_UPLOAD_ACTION_RSP
     */
    public native int onUploadActions(EventClickInfo[] infos, int version, String system, long seq);

    /**
     * 获取家庭成员UID列表
     * @param dev_uid 设备UID
     * @param seq
     * @return  返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_DEV_FAMILY_GET_MEM_LIST_RSP  数据：List<Integer>
     */
    public native int getFamilyMembers(int dev_uid, long seq);

    /**
     * a获取家庭成员信息
     * @param dev_uid
     * @param uid_list
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_DEV_FAMILY_GET_MEM_INFO_RSP  数据：List<FamilyMemberInfo>
     */
    public native int getFamilyMemberInfos(int dev_uid, int[] uid_list, long seq);

    /**
     * 控制机器人运动
     * @param cmd
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     */
    public native int onRobotMove(RobotCmd cmd, long seq);

    /**
     * 获取离线信息
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_GET_OFFLINE_CHAT_MSG_RSP  数据：ChatMessage
     * 回调命令： NetInfo.QHC_CMD_ADD_ASK_QUERY_RSP  数据：List<RequestFriend>
     * 回调命令： NetInfo.QHC_CMD_ADD_ANSWER_QUERY_RSP  数据：List<ResponseFriend>
     * 回调命令： NetInfo.QHC_CMD_GET_OFFLINE_SYSTEM_MSGS_RSP  数据：List<SystemMessage>
     * 回调命令： NetInfo.QHC_CMD_GROUP_MSG_PULL_RSP  数据：ChatMessage
     */
    public native int getOfflineMsg();

    /**
     * 创建聊天分组
     * @param groupInfo class GroupInfo
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     *  回调命令： NetInfo.QHC_CMD_GROUP_CREATE_RSP  数据：result
     */
    public native int createGroup(GroupInfo groupInfo, long seq);

    /**
     * 获取所有聊天分组的列表
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_GROUP_QUERY_ID_RSP  数据：List<Integer>
     */
    public native int getGroupList(long seq);

    /**
     * 获取所有聊天分组基本信息的列表
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_GROUP_QUERY_INFO_RSP  数据：List<GroupInfo>
     */
    public native int getGroupInfoList(List<Integer> groupIds, long seq);

    /**
     * 将群组保存到通讯录
     * @param groupId 群组ID
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_GROUP_SAVE_TO_CONTACTS_RSP  数据：result
     */
    public native int saveGroupToContacts(int groupId, long seq);

    /**
     * 将群组从通讯录移除
     * @param groupId 群组ID
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_GROUP_DEL_FROM_CONTACTS_RSP  数据：result
     */
    public native int delGroupFromContacts(int groupId, long seq);

    /**
     * 更新群组信息(群组管理员才可以进行此操作)
     * @param updateGroupInfo
     * updateGroupInfo->type 更新类型, 1-group_name, 2-announcement
     * updateGroupInfo->value 更新内容
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_GROUP_UPDATE_RSP  数据：result
     */
    public native int updateGroupInfo(UpdateGroupInfo updateGroupInfo, long seq);

    /**
     * 转移群组管理员(群组管理员才可以进行此操作)
     * @param groupId 群组ID
     * @param ownerUid 新管理员UID
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_GROUP_OWNERSHIP_TRANSFER_RSP  数据：result
     */
    public native int changeGroupOwner(int groupId, int ownerUid, long seq);

    /**
     * 群组二维码查询
     * @param groupId 群组ID
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_GROUP_QUERY_QRCODE_RSP  数据：GroupQRCode
     */
    public native int createGroupQRCode(int groupId, long seq);

    /**
     * 扫描二维码查询群组信息
     * @param qrcode 二维码
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_GROUP_QUERY_BY_QRCODE_RSP  数据：GroupInfo
     */
    public native int getGroupInfoByQRCode(String qrcode);

    /**
     * 扫描二维码加入群组
     * @param joinGroup
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_GROUP_JOIN_BY_QRCODE_RSP  数据：result
     */
    public native int joinGroupByQRCode(JoinGroup joinGroup, long seq);

    /**
     * 查询群组版本号 NetInfo.QHC_CMD_GROUP_VERSION_QUERY_RSP
     * @param groupIds 群组ID
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_GROUP_VERSION_QUERY_RSP  数据：List<GroupVersion>
     */
    public native int getGroupListVersion(List<Integer> groupIds, long seq);

    /**
     * 邀请/添加好友
     * @param inviteMembers
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_GROUP_INVITE_MEMBERS_RSP  数据：List<Integer> 失败列表
     */
    public native int inviteGroupMembers(InviteMembers inviteMembers, long seq);

    /**
     * 群组成员查询
     * @param groupId 群组ID
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_GROUP_MEMBER_QUERY_RSP  数据：List<GroupMember> 失败列表
     */
    public native int queryGroupMembers(int groupId, long seq);

    /**
     * 退出群组
     * @param groupId 群组ID
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_GROUP_LEAVE_RSP  数据：result
     */
    public native int leaveGroup(int groupId, long seq);

    /**
     * 删除群组成员
     * @param groupId 群组ID
     * @param members  群组成员UID
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_GROUP_DEL_MEMBERS_RSP  数据：result
     */
    public native int delGroupMembers(int groupId, List<Integer> members, long seq);

    /**
     * 更新群组成员信息
     * @param updateGroupMemberInfo
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_GROUP_UPDATE_MEMBER_INFO_RSP  数据：result
     */
    public native int updateGroupMemberInfo(UpdateGroupMemberInfo updateGroupMemberInfo, long seq);

    /**
     * 发送群组聊天消息
     * @param groupId 群组ID
     * @param data 发送内容
     * @param len 发送内容长度
     * @param type 信息类型
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     *  回调命令： NetInfo.QHC_CMD_GROUP_MSG_SEND_RSP
     */
    public native int onSendGroupMsg(int groupId, byte[] data, int len, int type, long seq);

    /**
     *  扫描PC端二维码
     * @param qrcode 二维码内容
     * @param seq 请求ID
     * @return 返回值：0-成功，其他-错误码
     *  回调命令： NetInfo.QHC_CMD_QRCODE_LINK_ACCOUNT_RSP
     */
    public native int pcLoginLinkQRCode(String qrcode, long seq);

    /**
     * 允许PC端登录
     * @param seq 请求ID
     * @return 返回值：0-成功，其他-错误码
     *  回调命令： NetInfo.QHC_CMD_ALLOW_PC_LOGIN_RSP
     */
    public native int allowPCLogin(long seq);

    /**
     * 强制PC端登出
     * @param seq 请求ID
     * @return 返回值：0-成功，其他-错误码
     *  回调命令： NetInfo.QHC_CMD_LOGOUT_PC_RSP
     */
    public native int logoutPC(long seq);

    /**
     * 设置推送维一标识
     * @param apnsId 极光注册ID
     * @param seq 请求ID
     * @return 返回值：0-成功，其他-错误码
     * NetInfo.QHC_CMD_SET_APNS_RSP
     */
    public native int setAPNS(String apnsId, long seq);

    /**
     * 机器人端扫二维码完成权限认证
     * @param loginRobot
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     */
    public native int robotScanToLogin(LoginRobot loginRobot, long seq);

    /**
     * 查询报警信息文件ID列表
     * @param alarmType
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_FILE_CHECK_DEV_RECORD_RSP 结果:List<Integer>
     */
    public native int queryAlarmRecord(AlarmType alarmType, long seq);

    /**
     * 查询报警信息文件ID列表
     * @param uid
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_FILE_CHECK_DEV_FILE_LIST_RSP 结果:List<Integer>
     */
    public native int queryRobotAlarmRecord(int uid, long seq);//qhc_query_dev_personal_file_list

    /**
     * 删除报警记录
     * @param alarmRecordInfo
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_FILE_DEL_DEV_RECORD_RSP
     */
    public native int removeAlarmRecord(AlarmRecordInfo alarmRecordInfo, long seq);

    /**
     * 通过报警文件ID列表查询文件信息
     * @param alarmFileIdList
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_QUERY_DEV_FILE_INFO_RSP 结果：List<AlarmFileInfo>
     */
    public native int queryAlarmRecordFileInfo(AlarmFileIdList alarmFileIdList, long seq);

    /**
     * 下载设备上传到云的文件
     * @param alarmFileInfo
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_DOWNLOAD_DEV_FILE_RSP 结果：DownloadFileResultInfo
     */
    public native int downloadRobotFileInfo(DownloadFileInfo alarmFileInfo, long seq);

    /**
     * 完成下载设备文件
     * @param finishDownloadFileInfo
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_FINISH_DOWN_FILE_RSP 结果：DownloadFileResultInfo
     */
    public native int finishDownloadRobotFileInfo(FinishDownloadFileInfo finishDownloadFileInfo, long seq);

    /**
     * 设置群免打扰状态
     * @param groupStatus
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_GROUP_SET_DISTURB_MODE_RSP 结果：
     */
    public native int setGroupDisturbMode(GroupStatus groupStatus, long seq);

    /**
     * 获取群组状态
     * @param groups
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_GROUP_GET_STATUS_RSP 结果：List<Integer>
     */
    public native int getGroupsDisturbMode(GroupsList groups, long seq);

    /**
     * 获取绑定手机号验证码
     * @param bindIdentifyInfo
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 阻塞方式：阻塞
     */
    public native int getBindIdentify(BindIdentifyInfo bindIdentifyInfo, long seq);

    /**
     * 绑定手机号
     * @param bindPhoneInfo
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 阻塞方式：阻塞
     */
    public native int bindPhone(BindPhoneInfo bindPhoneInfo, long seq);

	   /**
     * 获取共享机器人列表
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_GET_SHARE_SANBOT_RSP 结果：List<Integer>
     */
    public native int getShareSanbots(long seq);

    /**
     * 获取前台接待常用短语
     * @param seq
     * @return  返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_DEFAULT_REPLY_QUERY_RSP 结果：List<ReceptionReply>
     */
    public native int getReceptionReplys(long seq);

    /**
     * 添加前台接待短语
     * @param receptionReply
     * @param seq
     * @return  返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_DEFAULT_REPLY_INSERT_RSP 结果：
     */
    public native int addReceptionReplyInfo(ReceptionReply receptionReply, long seq);

    /**
     * 修改前台接待短语
     * @param receptionReply
     * @param seq
     * @return  返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_DEFAULT_REPLY_MODIFY_RSP 结果：
     */
    public native int editReceptionReplyInfo(ReceptionReply receptionReply, long seq);

    /**
     * 删除前台接待短语
     * @param replySeqs 短语seq列表
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_DEFAULT_REPLY_DELETE_RSP 结果：
     */
    public native int deleteReceptionReplyInfo(List<Integer> replySeqs, long seq);

    /**
     * 上传GPS位置信息
     * @param gpsInfo
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_UP_GPS_RSP　结果：
     */
    public native int uploadGPSInfo(GPSInfo gpsInfo, long seq);

    /**
     * 是否启用附近的人功能
     * @param status 状态, 0-open,1-close。
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_SET_PEOPLE_NEARBY_STATE_RSP
     */
    public native int enableNearby(int status, long seq);

    /**
     * 查询是否启用了附近的人功能
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_GET_PEOPLE_NEARBY_STATE_RSP 结果：Integer
     */
    public native int queryNearbyStatus(long seq);

    /**
     * 查询附近的人列表
     * @param seq
     * @return 返回值：0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_GET_PEOPLE_NEARBY_RSP 结果：List<NearByList>
     */
    public native int queryNearbyList(long seq);

    /**
     * 返回最后错误码
     * @return 返回值：0-成功，其他-错误码
     */
    public native int getLastError();

    /**
     * 给附近的人发送消息
     * @param uid 对方UID
     * @param data 发送内容
     * @param len 发送内容长度
     * @param type 信息类型
     * @param seq
     * @return
     */
    public native int onSendNearByMsg(int uid, byte[] data, int len, int type, long seq);

    /**
     * 获取登录成功的Token
     * @return Token
     */
    public native String getToken();

    /**
     * 创建公司
     * @param createCompany
     * @param seq
     * @return 0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_COMP_CREATE_COMPANY_RSP 结果：Integer
     */
    public native int createCompany(CreateCompany createCompany, long seq);

    /**
     * 修改公司信息
     * @param companyUpdateInfo
     * @param seq
     * @return 0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_COMP_MODIFY_COMPANY_INFO_RSP 结果：
     */
    public native int modifyCompany(CompanyUpdateInfo companyUpdateInfo, long seq);

    /**
     * 获取公司列表
     * @param seq
     * @return 0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_COMP_QUERY_COMPANYS_RSP 结果：List<CompanyInfo>
     */
    public native int queryCompany(long seq);

    /**
     * 根据公司ID查询公司基本信息
     * @param companyIds
     * @param seq
     * @return 0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_COMP_QUERY_COMPANY_INFO_RSP 结果：List<CompanyBaseInfo> 
     */
    public native int queryCompanyInfo(ArrayList<Integer> companyIds, long seq);

    /**
     * 修改部门信息
     * @param departmentInfo
     * @param seq
     * @return 0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_COMP_MODIFY_DEPARTMENT_RSP 结果：
     */
    public native int modifyDepartment(DepartmentInfo departmentInfo, long seq);

    /**
     * 查询公司成员信息
     * @param queryCompanyMember
     * @param seq
     * @return 0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_COMP_QUERY_MEMBERS_INFO_RSP 结果：List<CompanyMember>
     */
    public native int queryCompanyMembers(QueryCompanyMember queryCompanyMember, long seq);

    /**
     * 添加公司成员
     * @param addCompanyMember
     * @param seq
     * @return 0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_COMP_ADD_MEMBERS_RSP 结果：
     */
    public native int addCompanyMembers(AddCompanyMember addCompanyMember, long seq);

    /**
     * 创建部门
     * @param companyDepartment
     * @param seq
     * @return 0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_COMP_CREATE_DEPARTMENT_RSP 结果：DepartmentCreate
     */
    public native int createDepartment(CompanyDepartment companyDepartment, long seq);

    /**
     * 修改公司成员信息
     * @param modifyCompanyMember
     * @param seq
     * @return 0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_COMP_MODIFY_MEMBERS_RSP 结果：
     */
    public native int modifyCompanyMember(ModifyCompanyMember modifyCompanyMember, long seq);

    /**
     *  描述：修改团队机器人名称。
     *  阻塞方式： 非阻塞，立即返回, 回调结果。
     *  comp_id： [IN] 团队ID
     *  dev_type： [IN] 设备类型 1=机器人 2=前台服务器
     *  dev_uid： [IN] 设备UID
     *  name： [IN] 设备新名称
     *  msg_seq： [IN] 消息seq
     *  返回值： 0-成功，其他-错误码
     *  回调命令： QHC_CMD_COMP_MODIFY_DEV_NAME_RSP
     */
    public native int modifyCompanyDevice(CompanyDevice device, long seq);

    /**
     * 删除企业成员。
     * @param deleteCompanyMember
     * @param seq
     * @return 0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_COMP_DEL_MEMBERS_RSP 结果：
     */
    public native int deleteCompanyMembers(DeleteCompanyMember deleteCompanyMember, long seq);

    /**
     *添加企业机器人。
     * @param addCompanyRobot
     * @param seq
     * @return 0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_COMP_ADD_DEVICE_RSP 结果：
     */
    public native int addCompanyRobots(AddCompanyRobot addCompanyRobot, long seq);

    /**
     * 删除企业机器人。
     * @return 0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_COMP_DEL_DEVICE_RSP 结果：
     */
    public native int deleteCompanyRobots(DeleteCompanyMember deleteCompanyMember, long seq);

    /**
     * 删除企业部门。
     * @param deleteCompanyDepartment
     * @param seq
     * @return 0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_COMP_DEL_DEPARTMENTS_RSP 结果：
     */
    public native int deleteCompanyDepartments(DeleteCompanyDepartment deleteCompanyDepartment, long seq);

    /**
     * 发送企业聊天消息
     * @param uid 目标方user_id
     * @param data 控制命令数据
     * @param len 控制命令数据长度
     * @param type 消息类型
     * @param functionType 发送类型1 单聊（企业单聊） 2 群聊天（包括企业/部门聊天） 3 附近的人聊天
     * @param evType 使用环境:0-普通聊天,3-企业聊天
     * @param param string类型参数 如果企业聊天输入compannyId
     * @param seq
     * @return 0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_COMP_SEND_MSG_RSP 结果：
     */
    public native int onSendMessage(int uid, byte[] data, int len, int type, int functionType, int evType, String param,long seq);

    /**
     * 添加前台服务器
     * @param addCompanyServer
     * @param seq
     * @return 0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_COMP_ADD_FACESVR_RSP 结果：
     */
    public native int addCompanyServers(AddCompanyServer addCompanyServer, long seq);

    /**
     * 删除前台服务器
     * @param deleteCompanyServer
     * @param seq
     * @return 0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_COMP_DEL_FACESVR_RSP 结果：
     */
    public native int deleteCompanyServers(DeleteCompanyServer deleteCompanyServer, long seq);

    /**
     * 前台服务器是否可使用
     * @param serverUid
     * @param seq
     * @return 0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_COMP_SVR_USEABLE_RSP 结果：Integer 1:可用 0:不可用
     */
    public native int checkFaceServerUserable(int serverUid, long seq);

    /**
     * 机器人是否可使用应答
     * @param serverUid
     * @param seq
     * @return 0-成功，其他-错误码
     * 回调命令： NetInfo.QHC_CMD_COMP_DEV_USEABLE_RSP 结果：Integer 1:可用 0:不可用
     */
    public native int checkRobotUserable(int serverUid, long seq);

    /**
     *  描述：检查账号密码是否正确。
     *  阻塞方式： 非阻塞，立即返回, 回调结果。
     *  accountType： 账号类型。
     *  account： 账号。
     *  password： 密码。
     *  msg_seq： [IN] 消息seq
     *  返回值： 0-成功，其他-错误码
     *  回调命令： QHC_CMD_CHECK_ACCOUNT_PASSWORD_RSP
     */
    public native int checkAccountPassword(LoginAccount accountInfo, long seq);



    /**
     *  描述：修改绑定手机/邮箱
     *  阻塞方式： 非阻塞，立即返回, 回调结果。
     *  type： 修改类型1-手机号, 2-邮箱。
     *  newValue： 新值。
     *  identifyCode： 验证码。
     *  msg_seq： [IN] 消息seq
     *  返回值： 0-成功，其他-错误码
     *  回调命令： QHC_CMD_MODIFY_BINDING_MODE_RSP
     */
     public native int bindNewData(BindData data, long seq);

    /**
     *  描述：获取绑定手机验证码。
     *  阻塞方式： 非阻塞，立即返回, 回调结果。
     *  tel： [IN] 新手机号
     *  msg_seq： [IN] 消息seq
     *  返回值： 0-成功，其他-错误码
     *  回调命令： QHC_CMD_GET_ASSOCIATED_TEL_IDENTIFY_RSP
     */
     public native int getNewPhoneIndetify(String phone, long seq);

    /**
     *  描述：更换前台服务器。
     *  阻塞方式： 非阻塞，立即返回, 回调结果。
     *  comp_id： [IN] 团队ID
     *  old_svr： [IN] 原有服务器
     *  new_svr： [IN] 新服务器
     *  msg_seq： [IN] 消息seq
     *  返回值： 0-成功，其他-错误码
     *  回调命令： QHC_CMD_CHANGE_SVR_RSP
     *  通知：QHC_CMD_COMP_CHANGE_SVR_NOTE 结果：CompanyServerNote
     */
    public native int changeCompanyServer(ChangeServerInfo changeServerInfo, long seq);

    /**
     * 注销帐号
     * @param closeAccount
     * @return 返回值： 0-成功，其他-错误码
     */
    public native int closeAccount(CloseAccount closeAccount);

    /**
     * 获取注销帐号验证码
     * @param closeAccountIdentify
     * @return *  type： [OUT] 发送消息类型，当返回值为0时，1为发送手机验证码 2为发送邮箱验证码。
     *  返回值： 0-成功，其他-错误码
     */
    public native int getCloseAccountIdentify(CloseAccountIdentify closeAccountIdentify);

    /**
     * 在线接收到的消息
     *  回调命令： NetInfo.QHC_CMD_RECV_CHAT_MSG ChatMessage
     *  回调命令： NetInfo.QHC_CMD_RECV_ADD_ASK_REQ RequestFriend
     *  回调命令： NetInfo.QHC_CMD_RECV_ADD_ANSWER_REQ AnswerMsg
     *  回调命令： NetInfo.QHC_CMD_SYSTEM_MSG_REQ SystemMessage
     */

    /**
     * 公司通知的消息
     *  回调命令： NetInfo.QHC_CMD_COMP_CREATE_COMPANY_NOTE CompanyBaseInfo
     *  回调命令： NetInfo.QHC_CMD_COMP_MODIFY_COMPANY_INFO_NOTE CompanyUpdateInfo
     *  回调命令： NetInfo.QHC_CMD_COMP_CREATE_DEPARTMENT_NOTE Department
     *  回调命令： NetInfo.QHC_CMD_COMP_MODIFY_DEPARTMENT_NOTE Department
     *  回调命令： NetInfo.QHC_CMD_COMP_ADD_MEMBERS_NOTE List<CompanyMember>
     *  回调命令： NetInfo.QHC_CMD_COMP_MODIFY_MEMBERS_NOTE CompanyMember
     *  回调命令： NetInfo.QHC_CMD_COMP_ADD_DEVICE_NOTE CompanyAddInfo
     *  回调命令： NetInfo.QHC_CMD_COMP_ADD_LOCALSVR_NOTE CompanyAddInfo
     *  回调命令： NetInfo.QHC_CMD_COMP_DELETE_NOTE CompanyDeleteInfo
     *  回调命令： NetInfo.QHC_CMD_COMP_JOIN_NOTE Integer 公司id
     */

    static {
        try {
            System.loadLibrary("SanbotNetApi");
        } catch (Exception ex) {
            System.out.println("Load ConnectClient: " + ex.toString());
        }
    }

    // 产生UUID字符串
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    public void showLog(byte[] data)
    {
        //QHLog.i("QHSDK", "SO LOG:"+new String(data));
    }

    public void showRecv(int cmd, int result, Object obj, long seq)
    {
        if (rListener != null) {
            rListener.showRecv(cmd, result, obj, seq);
        }
    }

    public void showVideo(int handle, byte[] data, int len, int width, int height, int sec, int type)
    {
        if (videoListener != null) {
            videoListener.showVideo(handle, data, len, width, height,sec, type);
        }
    }

    public void setrListener(ShowRecvListener rListener) {
        this.rListener = rListener;
    }

    public interface ShowVideoListener {
        void showVideo(int handle, byte[] data, int len, int width, int height, int sec, int type);
    }
}
