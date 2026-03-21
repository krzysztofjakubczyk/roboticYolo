package com.sanbot.net;

/**
 * Created by QHPC on 2016/4/20.
 */
public class NetInfo {

    //20160629
    public static final int CMD_ACCOUNT_DEV_MG_FRIEND_DEL_REQ = 0x20765;//设备管理者删除设备好友请求
    public static final int CMD_ACCOUNT_DEV_MG_FRIEND_DEL_RSP = 0x20766;//设备管理者删除设备好友响应
    public static final int CMD_ACCOUNT_DEV_MG_FRIEND_REMARKS_MODIFY_REQ = 0x20767;//设备管理者修改设备好友remarks请求
    public static final int CMD_ACCOUNT_DEV_MG_FRIEND_REMARKS_MODIFY_RSP = 0x20768;//设备管理者修改设备好友remarks响应
    public static final int CMD_ACCOUNT_DEV_MG_FRIEND_PERMISSION_MODIFY_REQ = 0x20769;//设备管理者修改设备好友权限请求
    public static final int CMD_ACCOUNT_DEV_MG_FRIEND_PERMISSION_MODIFY_RSP = 0x20770;//设备管理者修改设备好友权限响应
    public static final int CMD_ACCOUNT_DEV_MG_GET_DEV_FRI_BASEINFO_REQ = 0x20771;//设备管理者获取设备好友基本信息请求
    public static final int CMD_ACCOUNT_DEV_MG_GET_DEV_FRI_BASEINFO_RSP = 0x20772;//设备管理者获取设备好友基本信息响应
    public static final int CMD_ACCOUNT_DEV_MG_GET_DEV_FRI_CNT_REQ = 0x20779;//设备管理者获取设备好友数量请求
    public static final int CMD_ACCOUNT_DEV_MG_GET_DEV_FRI_CNT_RSP = 0x20780;//设备管理者获取设备好友数量响应

    public static final int CMD_SERVER_SEND_ALARM_REQ = 0x10603;//接收设备端报警消息
    public static final int CMD_SERVER_SEND_ALARM_RSP = 0x10604;

    public static final int CMD_UPLOAD_CONTACTS_REQ = 0x20793;//上传通讯录请求
    public static final int CMD_UPLOAD_CONTACTS_RSP = 0x20794;//上传通讯录响应
    public static final int ROBOT_NO_RENTAL = -6;
    public static final int ROBOT_LOCKED = -7;
    public static final int CMD_UPLOAD_QL_ACTION_REQ = 0x20795;//QLINK操作记录请求
    public static final int CMD_UPLOAD_QL_ACTION_RSP = 0x20796;//QLINK操作记录响应

    public static final int CONTENT_TYPE_TEXT = 0;// 文字
    public static final int CONTENT_TYPE_AUDIO = 1;// 语音
    public static final int CONTENT_TYPE_IMAGE = 2;// 图片
    public static final int CONTENT_TYPE_FILE = 3;//文件
    public static final int CONTENT_TYPE_AUDIO_FILE = 4;//语音文件
    public static final int CONTENT_TYPE_MPS = 5;//mps消息
    public static final int CONTENT_TYPE_DUMI = 6;//DUMI消息

    //mps消息类型
    public static final int CONTENT_TYPE_PPT = 6; //ppt
    public static final int CONTENT_TYPE_SHARE = 22;//群共享
    public static final int CONTENT_TYPE_ROBOT = 24;//来自机器人
    public static final int CONTENT_TYPE_DACNE = 31; //舞蹈
    public static final int CONTENT_TYPE_MUSIC = 32; //音乐
    public static final int CONTENT_TYPE_SCHEDULE = 33; //日程
    public static final int CONTENT_TYPE_HORN = 34; //小喇叭

    public static final int CONTENT_TYPE_LIVE_VIDEO = 10;// 视频聊天
    public static final int CONTENT_TYPE_LIVE_AUDIO = 11;// 语音聊天

    public final static int FILE_TYPE_CHAT = 0;//聊天图片
    public final static int FILE_TYPE_ICON = 1;//头像
    public final static int FILE_TYPE_FILE = 2;//文件
    public final static int FILE_TYPE_AUDIO = 3;//語音
    public final static int RECORD_VIDEO = 4; //报警记录视频
    public final static int RECORD_PICTURE = 5; //报警记录图片


    public static final int CONTENT_TYPE_LIVE_AGREE = 12;// 同意视频或者语音聊天请求
    public static final int CONTENT_TYPE_LIVE_REFUSE = 13;// 拒绝视频或者语音聊天请求
    public static final int CONTENT_TYPE_LIVE_CANCEL = 14;// 取消视频或者语音聊天
    public static final int CONTENT_TYPE_LIVE_TALKING = 15;// 对方正在通话
    public static final int CONTENT_TYPE_ALARM_MESSAGE = 20;// 报警信息
    public static final int CONTENT_TYPE_NOTICE_MESSAGE = 21;//通知信息
    public static final int CONTENT_TYPE_ALERT_MESSAGE = 22;//任务执行通知
    public static final int CONTENT_TYPE_RECEPTION_MESSAGE = 23;//三宝呼叫
    public static final int CONTENT_TYPE_EMOTICON_MESSAGE = 24;//发送表情
    public static final int CONTENT_TYPE_TALK_MESSAGE = 25;//与机器人聊天回复
    public static final int CONTENT_TYPE_STRANGER_MESSAGE = 0x7001;//不是好友

    public static final int VIDEO_TALK_MODE_REQUEST = 0x1001;
    public static final int VIDEO_TALK_MODE_ANSWER = 0x1002;
    public static final int VIDEO_TALK_MODE_PAUSE = 0x1003;
    public static final int VIDEO_TALK_MODE_STOP = 0x1004;
    public static final int VIDEO_TALK_MODE_START = 0x1005;
    public static final int VIDEO_TALK_AGREE = 0x1006;
    public static final int VIDEO_TALK_TEMP = 0x1007;
    public static final int VIDEO_SCREEN_ACTION = 0x1008;
    public static final int VIDEO_REQUEST_CANCEL = 0x1009;
    public static final int VIDEO_REQUEST_CLOSE = 0x1010;
    public static final int QR_CODE = 0x1011;
    public static final int QUERY_FRIEND_INFO = 0x1012;
    public static final int VIDEO_ANSWER_CANCEL = 0x1013;
    public static final int VIDEO_ANSWER_CLOSE = 0x1014;
    public static final int UPDATE_PASSWORD = 0x1015;
    public static final int INIT_PARAMS = 0x1016;
    public static final int COMMON_ERROR_CODE_APP_VERSION = 1900;
    public static final int RESULT_ERROR = -1;//请求失败
    public static final int FORBIDDEN = -2;//没有权限
    public static final int CONFLICT = -3;//功能冲突
    public static final int INVALID_PARAMETER = -4;//参数错误
    public static final int DEL_HORN_ERROR = -5;//当前小喇叭正在播放，无法删除
    public static final int ROBOT_NOT_LEASED_ERROR = -6;//机器人没有租赁
    public static final int ROBOT_LACKED_ERROR = -7;//机器人被锁定
    public static final int NOT_ACTIVE_PERMISSION = -10;//您还没有激活权限管理功能
    public static final int FORBIDDEN_MOVE_BECAUSE_USB = -101;//没有权限
    public static final int BUSY_ERROR = 400001;//操作频繁
    public static final int RESULT_OK = 0;//成功状态
    public static final int RESULT_SMS_OK = 410003;// 短信登陆成功
    public static final int ERROR_PARAM_EXCEPTION = 410005;// 参数错误
    public static final int LOGIN_RECEIVE_ERROR_ORDER = 410007;// 收到错误的命令
    public static final int QLINK_COMM_LOGIN_RSP = 1;//登录命令
    public static final int QLINK_COMM_GET_LOGIN_IDENTIFY_RSP = 2;//获取登录验证码
    public static final int QLINK_COMM_LOGIN_BY_IDENTIFY_RSP = 3;//通过验证码登录
    public static final int QLINK_COMM_FORCE_LOGOUT_REQ = 4;//强制登出

    public static final String LOGIN_STATUS = "com.qhcloud.login_status";
    public static final String FRAGMENT_STATUS = "com.qhcloud.fragment_status";
    public static final String UPLOADLIST_STATUS = "com.qhcloud.uploadList_status";
    public static final String SYSTEM_LOGOUT = "com.qhcloud.logout";
    public static final String VIDEO_TALK_HEARTBEAT = "com.qhcloud.video.heartbeat";
    public static final String VIDEO_TALK_CLOSE = "com.qhcloud.video.close";

    //回调命令
    public static final int QHC_CMD_LOGIN_RSP = 1;//登录结果回调命令					--回调错误码
    public static final int QHC_CMD_GET_LOGIN_IDENTIFY_RSP = 2;//获取登录验证码结果回调命令			--暂未使用
    public static final int QHC_CMD_LOGIN_BY_IDENTIFY_RSP = 3;//验证码登录结果回调命令				--暂未使用
    public static final int QHC_CMD_FORCE_LOGOUT_REQ = 4;//收到强制退出命令					--收到该命令即为被踢下线，无需判断错误码，data为NULL
    public static final int QHC_CMD_ADD_ASK_RSP = 5;//添加好友消息结果回调				--回调错误码
    public static final int QHC_CMD_RECV_ADD_ASK_REQ = 6;//收到添加好友消息命令				--回调data,数据类型为(qhc_add_ask_req_t*)
    public static final int QHC_CMD_ADD_ANSWER_RSP = 7;//应答添加好友消息结果回调				--回调错误码
    public static final int QHC_CMD_RECV_ADD_ANSWER_REQ = 8;//收到应答添加好友消息回调命令			--回调data,数据类型为(qhc_add_answer_req_t*)
    public static final int QHC_CMD_DELETE_FIREND_RSP = 9;//删除好友结果回调					--回调错误码
    public static final int QHC_CMD_GET_FRIEND_LIST_RSP = 10;//获取好友列表消息回调				--回调错误码/data,数据类型为(qhc_friend_list_t*)
    public static final int QHC_CMD_ADD_ASK_QUERY_RSP = 11;//获取离线添加好友消息回调			--回调错误码/data,数据类型为(add_ask_req_list_t*)
    public static final int QHC_CMD_ADD_ASK_DELETE_RSP = 12;//删除离线添加好友消息结果回调		--回调错误码
    public static final int QHC_CMD_ADD_ANSWER_QUERY_RSP = 13;//获取离线应答添加好友消息回调		--回调错误码/data,数据类型为(qhc_answer_list_t*)
    public static final int QHC_CMD_ADD_ANSWER_DELETE_RSP = 14;//删除离线应答添加好友消息结果回调		--回调错误码
    public static final int QHC_CMD_GET_FRIEND_VERSION_RSP = 15;//获取好友版本信息回调				--回调错误码/data,数据类型为(qhc_friend_version_list_t*)
    public static final int QHC_CMD_GET_FRIEND_BASE_INFO_QUERY_RSP = 16;//获取好友基本信息回调				--回调错误码/data,数据类型为(qhc_base_info_list_t*)
    public static final int QHC_CMD_GET_FRIEND_REMARKS_INFO_QUERY_RSP = 17;//获取好友备注消息回调				--回调错误码/data,数据类型为(qhc_remark_info_list_t*)
    public static final int QHC_CMD_SET_FRIEND_REMARKS_RSP = 18;//设置好友备注消息结果回调			--回调错误码
    public static final int QHC_CMD_UID_QUERY_RSP = 19;//根据账号查询UID消息回调			--回调错误码/data,数据类型为(qhc_user_id_t*)
    public static final int QHC_CMD_ACCOUNT_QUERY_RSP = 20;//查询账号信息回调					--回调错误码/data,数据类型为(account_info_t*)
    public static final int QHC_CMD_ACCOUNT_MODIFY_RSP = 21;//修改账号信息结果回调				--回调错误码
    public static final int QHC_CMD_SEND_CHAT_MSG_RSP = 22;//发送聊天消息结果回调				--回调错误码
    public static final int QHC_CMD_RECV_CHAT_MSG = 23;//收到聊天消息						--回调data,数据类型为(qhc_chat_msg_list_t*)
    public static final int QHC_CMD_GET_OFFLINE_CHAT_MSG_RSP = 24;//获取离线聊天消息回调				--回调错误码/data,数据类型为(qhc_unread_chat_msg_list_t*)
    public static final int QHC_CMD_DEL_OFFLINE_CHAT_MSG_RSP = 25;//删除离线聊天消息结果回调			--回调错误码
    public static final int QHC_CMD_CTRL_MSG_RSP = 26;//收到控制消息应答回调				--回调错误码/data,数据类型为(qhc_ctrl_msg_t*)
    public static final int QHC_CMD_ONLINE_STATUS = 27;//在线状态回调						--回调错误码(表示在线状态)
    public static final int QHC_CMD_MODIFY_PASSWORD_RSP = 28;//修改密码结果回调					--回调错误码
    public static final int QHC_CMD_GET_DEV_FRIEND_CNT_RSP = 29;//获取设备好友列表回调				--回调错误码/data,数据类型为(qhc_friend_list_t*)
    public static final int QHC_CMD_DEL_DEV_FRIEND_RSP = 31;//删除设备好友消息结果回调			--回调错误码
    public static final int QHC_CMD_MODIFY_DEV_FREMARKS = 32;//修改设备好友备注信息结果回调		--回调错误码
    public static final int QHC_CMD_MODIFY_DEV_FPERMISSION = 33;//修改设备好友权限结果回调			--回调错误码
    public static final int QHC_CMD_GET_DEV_FINFO_RSP = 34;//获取设备好友信息回调				--回调错误码/data,数据类型为(qhc_dev_friend_base_info_list_t*)
    public static final int QHC_CMD_UPLOAD_ACTION_RSP = 36;//上传点击动作记录结果回调				--回调错误码
    public static final int QHC_CMD_CTRL_MSG = 37;//收到双向控制消息					--回调data,数据类型为(qhc_ctrl_msg_t*),需要在回调中回复
    public static final int QHC_CMD_ONE_CTRL_MSG = 38;//收到单向控制消息					--回调data,数据类型为(qhc_ctrl_msg_t*)
    public static final int QHC_CMD_UPLOAD_FILE_RSP = 39;//文件上传应答						--回调错误码/data,数据类型为(qhc_file_upload_rsp_t*)
    public static final int QHC_CMD_FINISH_UPLOAD_FILE_RSP = 40;//文件上传结束应答					--回调错误码/data,数据类型为(qhc_file_finish_upload_rsp_t*)
    public static final int QHC_CMD_DOWN_FILE_RSP = 41;//文件下载应答						--回调错误码/data,数据类型为(qhc_file_download_rsp_t*)
    public static final int QHC_CMD_FINISH_DOWN_FILE_RSP = 42;//文件下载结束应答					--回调错误码
    public static final int QHC_CMD_GET_PERSONAL_FILE_LIST = 43;//获取私人文件列表应答				--回调错误码/data,数据类型为(qhc_file_id_list_t*)
    public static final int QHC_CMD_GET_PERSONAL_FILE_INFO = 44;//获取私人文件信息应答				--回调错误码/data,数据类型为(qhc_file_info_list_t*)
    public static final int QHC_CMD_DEL_PERSONAL_FILE = 45;//删除私人文件应答					--回调错误码
    public static final int QHC_CMD_SET_APNS_RSP = 46;//设置aps							--回调错误码
    public static final int QHC_CMD_GET_ONLINE_RSP = 47;//获取在线状态应答					--回调错误码/data,数据类型为(qhc_online_state_list_t*)
    public static final int QHC_CMD_GET_FILE_TASK_RSP = 48;//获取文件任务列表应答				--回调错误码/data,数据类型为(qhc_file_task_list_t*)
    public static final int QHC_CMD_CANCEL_FILE_TASK_RSP = 49;//取消文件任务列表应答				--回调错误码
    public static final int QHC_CMD_GET_MODIFY_MGR_RSP = 50;//获取修改管理员短信应答
    public static final int QHC_CMD_GET_OFFLINE_SYSTEM_MSGS_RSP = 56;//系统消息查询应答
    public static final int QHC_CMD_SYSTEM_MSG_REQ = 58;//系统通知消息						--回调错误码/data,数据类型为(qhc_sys_msg_info_t*)
    public static final int QHC_CMD_TRANSFER_ADMIN_RSP = 51; // 权限转移应答码
    public static final int QHC_CMD_TRANSFER_SAFECODE = 50; // 短信验证码发送应答

    public static final int QHC_CMD_GROUP_CREATE_RSP = 62;//创建群组应答						--回调错误码/data,数据类型为(qhc_group_create_info_t*)
    public static final int QHC_CMD_GROUP_QUERY_ID_RSP = 63;//群组ID查询应答						--回调错误码/data,数据类型为(qhc_group_id_list_t*)
    public static final int QHC_CMD_GROUP_QUERY_INFO_RSP = 64;//群组信息查询应答					--回调错误码/data,数据类型为(qhc_group_info_list_t*)
    public static final int QHC_CMD_GROUP_SAVE_TO_CONTACTS_RSP = 65;//将群组保存到通讯录应答				--回调错误码
    public static final int QHC_CMD_GROUP_DEL_FROM_CONTACTS_RSP = 66;//将群组从通讯录移除应答				--回调错误码
    public static final int QHC_CMD_GROUP_UPDATE_RSP = 67;//更新群组信息应答					--回调错误码
    public static final int QHC_CMD_GROUP_OWNERSHIP_TRANSFER_RSP = 68;//转移群组管理员应答					--回调错误码
    public static final int QHC_CMD_GROUP_QUERY_QRCODE_RSP = 69;//群组二维码查询应答					--回调错误码/data,数据类型为(qhc_group_qrcode_t*)
    public static final int QHC_CMD_GROUP_QUERY_BY_QRCODE_RSP = 70;//扫描二维码查询群组信息应答			--回调错误码/data,数据类型为(qhc_group_info_t*)
    public static final int QHC_CMD_GROUP_JOIN_BY_QRCODE_RSP = 71;//扫描二维码加入群组应答				--回调错误码
    public static final int QHC_CMD_GROUP_VERSION_QUERY_RSP = 72;//查询群组版本号应答					--回调错误码/data,数据类型为(qhc_group_version_list_t*)
    public static final int QHC_CMD_GROUP_INVITE_MEMBERS_RSP = 73;//邀请/添加好友应答					--回调错误码/data,数据类型为(qhc_group_add_fail_list_t*)
    public static final int QHC_CMD_GROUP_MEMBER_QUERY_RSP = 74;//群组成员查询应答					--回调错误码/data,数据类型为(qhc_group_member_list_t*)
    public static final int QHC_CMD_GROUP_LEAVE_RSP = 75;//退出群组应答						--回调错误码
    public static final int QHC_CMD_GROUP_DEL_MEMBERS_RSP = 76;//删除群组成员应答					--回调错误码
    public static final int QHC_CMD_GROUP_UPDATE_MEMBER_INFO_RSP = 77;//更新群组成员信息应答				--回调错误码

    public static final int QHC_CMD_GROUP_UPDATE_INFO_NOTE = 78;//群组基本信息更新通知				--回调data,数据类型为(qhc_group_update_note_t*)
    public static final int QHC_CMD_GROUP_OWNERSHIP_TRANSFER_NOTE = 79;//群组管理员转移通知					--回调data,数据类型为(qhc_group_ownership_transfer_note_t*)
    public static final int QHC_CMD_GROUP_MEMBERS_INFO_UPDATE_NOTE = 80;//群组成员信息更新通知				--回调data,数据类型为(qhc_group_members_info_update_note_t*)
    public static final int QHC_CMD_GROUP_JOIN_BY_QRCODE_NOTE = 81;//通过扫描二维码加入群组通知			--回调data,数据类型为(qhc_group_join_by_qrcode_note_t*)
    public static final int QHC_CMD_GROUP_ADD_MEMBERS_NOTE = 82;//群组成员添加通知					--回调data,数据类型为(qhc_group_add_members_note_t*)
    public static final int QHC_CMD_GROUP_DEL_MEMBERS_NOTE = 83;//群组成员删除通知					--回调data,数据类型为(qhc_group_del_members_note_t*)
    public static final int QHC_CMD_GROUP_MEMBER_LEAVE_NOTE = 84;//群组成员退出通知					--回调data,数据类型为(qhc_group_member_leave_note_t*)

    public static final int QHC_CMD_GROUP_MSG_SEND_RSP = 85;//发送群组聊天消息应答				--回调错误码
    public static final int QHC_CMD_GROUP_MSG_RECV_REQ = 86;//收到群组聊天消息					--回调data,数据类型为(qhc_chat_msg_list_t*)
    public static final int QHC_CMD_GROUP_MSG_PULL_RSP = 87;//拉取离线群组聊天消息应答				--回调错误码/data,数据类型为(qhc_unread_chat_msg_list_t*)
    public static final int QHC_CMD_GROUP_MSG_UPDATE_RSP = 88;//删除离线群组聊天消息应答				--回调错误码

    public static final int QHC_CMD_MPS_RECV_MSGS = 89;//收到MPS消息						--回调data,数据类型为(qhc_chat_msg_list_t*)
    public static final int QHC_CMD_MPS_GET_OFFLINE_MSG_RSP = 90;//获取离线MPS消息回调				--回调错误码/data,数据类型为(qhc_unread_chat_msg_list_t*)
    public static final int QHC_CMD_MPS_DEL_OFFLINE_MSG_RSP = 91;//删除离线MPS消息结果回调				--回调错误码
    public static final int QHC_CMD_PC_LOGIN_NOTE = 92;//PC登录通知
    public static final int QHC_CMD_ALLOW_PC_LOGIN_RSP = 93;//手机允许pc登录消息应答				--回调错误码
    public static final int QHC_CMD_QRCODE_LINK_ACCOUNT_RSP = 94;//二维码关联账户响应					--回调错误码
    public static final int QHC_CMD_LOGOUT_PC_RSP = 95;//手机退出pc登录请求响应				--回调错误码
    public static final int QHC_CMD_PC_ONLINE_STATUE_NOTE = 96;//PC客户端在线状态通知消息				--data,数据类型为(uint32_t*)
    public static final int QHC_CMD_QUERY_DEV_FILE_INFO_RSP = 97;//管理员获取机器人私人文件信息应答
    public static final int QHC_CMD_FILE_CHECK_DEV_RECORD_RSP = 89;//查询机器人录像文件列表				--回调错误码/data,数据类型为(qhc_file_id_list_t*)
    public static final int QHC_CMD_FILE_DEL_DEV_RECORD_RSP = 90;//管理者删除设备文件应答				--回调错误码
    public static final int QHC_CMD_GET_CLIENT_VERSION_RSP = 61;//获取好友客户端版本应答
    public static final int QHC_CMD_GET_SINGLE_FRIEND_BASE_INFO_QUERY_RSP = 10000;//获取好友基本信息回调				--回调错误码/data,数据类型为(qhc_base_info_list_t*)
    public static final int QHC_CMD_GROUP_CREATE_ON_OTHER_END_NOTE = 100;//在其他端创建群通知					--回调data,数据类型为(qhc_create_group_note_t*)
    public static final int QHC_CMD_FILE_CHECK_DEV_FILE_LIST_RSP = 101;//管理员获取设备个人文件id列表		--回调错误码/data,数据类型为(qhc_file_id_list_t*)
    public static final int QHC_CMD_GROUP_SET_DISTURB_MODE_RSP = 102;//设置群组免打扰模式应答				--回调错误码
    public static final int QHC_CMD_GROUP_GET_STATUS_RSP = 103;//获取群组状态						--回调错误码/data,数据类型为(qhc_group_id_list_t*)
    public static final int QHC_CMD_GROUP_QUERY_NOTIFICATIONS_RSP = 104;//收到群通知离线消息 List<GroupNoteOfflineMessage>
    public static final int QHC_CMD_GROUP_UPDATE_NOTIFICATIONS_RSP = 105;//
    public static final int QHC_CMD_RECV_MPS_MSG = 106;//收到mps消息	 List<MpsMessage>					--回调data,数据类型为(qhc_mps_msg_list_t*)
    public static final int QHC_CMD_PULL_OFFLINE_MPS_RSP = 107;//拉取离线mps消息应答 List<MpsMessage>				--回调错误码/data,数据类型为(qhc_mps_msg_list_t*)
    public static final int QHC_CMD_GET_SHARE_SANBOT_RSP = 110;//共享机器人列表查询应答				--回调data,数据类型为(qhc_friend_list_t*)
    public static final int QHC_CMD_DEFAULT_REPLY_QUERY_RSP = 111;//获取前台接待常用短语
    public static final int QHC_CMD_DEFAULT_REPLY_INSERT_RSP = 114;//添加前台接待短语
    public static final int QHC_CMD_DEFAULT_REPLY_MODIFY_RSP = 112;//修改前台接待短语
    public static final int QHC_CMD_DEFAULT_REPLY_DELETE_RSP = 113;//删除前台接待短语

    public static final int QHC_CMD_ACCOUNT_INFO_MODIFY_NOTE = 115;//账户信息修改通知					--回调data,数据类型为(qhc_account_info_motify_t*)
    public static final int QHC_CMD_FRIEND_INFO_MODIFY_NOTE = 116;//好友信息修改通知					--回调data,数据类型为(qhc_friend_info_motify_t*)
    public static final int QHC_CMD_GET_PEOPLE_NEARBY_RSP = 117;//客户端拉取附近的人列表应答			--回调错误码/data,数据类型为(qhc_people_nearby_list*)
    public static final int QHC_CMD_SEND_PEOPLE_NEARBY_MSG_RSP = 118;//客户端给附近的人发送消息应答			--回调错误码
    public static final int QHC_CMD_RECV_PEOPLE_NEARBY_MSG = 119;//客户端收到附近的人消息				--回调data,数据类型为(qhc_chat_msg_t*)
    public static final int QHC_CMD_SET_PEOPLE_NEARBY_STATE_RSP = 120;//客户端设置附近的人状态应答			--回调错误码
    public static final int QHC_CMD_GET_PEOPLE_NEARBY_STATE_RSP = 121;//客户端获取附近的人状态应答			--回调错误码/date,数据类型为(qhc_people_nearby_status*)
    public static final int QHC_CMD_UP_GPS_RSP = 122;//客户端上报GPS位置信息应答			--回调错误码

    public static final int QHC_CMD_COMP_CREATE_COMPANY_RSP = 123;//创建企业应答						--回调错误码/data, 数据类型为(qhc_comp_create_rsp*)
    public static final int QHC_CMD_COMP_MODIFY_COMPANY_INFO_RSP = 124;//修改企业信息应答					--回调错误码
    public static final int QHC_CMD_COMP_QUERY_COMPANYS_RSP = 125;//查询所有企业应答					--回调错误码/data, 数据类型为(qhc_comp_query_tol_rsp*)
    public static final int QHC_CMD_COMP_QUERY_COMPANY_INFO_RSP = 126;//查询企业信息应答					--回调错误码/data, 数据类型为(qhc_comp_info_list*)
    public static final int QHC_CMD_COMP_CREATE_DEPARTMENT_RSP = 127;//创建部门应答						--回调错误码/data, 数据类型为(qhc_comp_dept_add_rsp*)
    public static final int QHC_CMD_COMP_MODIFY_DEPARTMENT_RSP = 128;//修改部门信息应答					--回调错误码
    public static final int QHC_CMD_COMP_ADD_MEMBERS_RSP = 129;//添加企业成员应答					--回调错误码
    public static final int QHC_CMD_COMP_QUERY_MEMBERS_INFO_RSP = 130;//查询成员信息应答					--回调错误码/data, 数据类型为(qhc_query_comp_members_info_rsp*)
    public static final int QHC_CMD_COMP_MODIFY_MEMBERS_RSP = 131;//修改成员信息应答					--回调错误码
    public static final int QHC_CMD_COMP_SEND_MSG_RSP = 132;//发送企业聊天消息应答				--回调错误码
    public static final int QHC_CMD_COMP_DEL_MEMBERS_RSP = 135;//删除企业成员				--回调错误码
    public static final int QHC_CMD_COMP_DEL_DEPARTMENTS_RSP = 136;//删除企业部门				--回调错误码
    public static final int QHC_CMD_COMP_ADD_FACESVR_RSP = 137;//添加人脸识别服务器应答				--回调错误码
    public static final int QHC_CMD_COMP_DEL_FACESVR_RSP = 138;//删除人脸识别服务器应答				--回调错误码
    public static final int QHC_CMD_COMP_ADD_DEVICE_RSP = 133;//添加企业机器人应答				--回调错误码
    public static final int QHC_CMD_COMP_DEL_DEVICE_RSP = 134;//删除企业机器人应答				--回调错误码

    public static final int QHC_CMD_COMP_CREATE_COMPANY_NOTE = 139;//创建企业通知						--回调data, 数据类型为(qhc_comp_create_company_note*)
    public static final int QHC_CMD_COMP_MODIFY_COMPANY_INFO_NOTE = 140;//修改企业信息通知					--回调data, 数据类型为(qhc_comp_modify_comp_info_note*)
    public static final int QHC_CMD_COMP_CREATE_DEPARTMENT_NOTE = 141;//创建部门通知						--回调data, 数据类型为(qhc_comp_create_department_note*)
    public static final int QHC_CMD_COMP_MODIFY_DEPARTMENT_NOTE = 142;//修改部门信息通知					--回调data, 数据类型为(qhc_comp_modify_dept_info_note*)
    public static final int QHC_CMD_COMP_ADD_MEMBERS_NOTE = 143;//添加企业成员通知					--回调data, 数据类型为(qhc_comp_add_members_note*)
    public static final int QHC_CMD_COMP_MODIFY_MEMBERS_NOTE = 144;//修改企业成员信息通知				--回调data, 数据类型为(qhc_comp_modify_member_info_note*)
    public static final int QHC_CMD_COMP_ADD_DEVICE_NOTE = 145;//添加企业机器人通知					--回调data, 数据类型为(qhc_comp_add_device_note*)
    public static final int QHC_CMD_COMP_ADD_LOCALSVR_NOTE = 146;//添加前台服务器通知					--回调data, 数据类型为(qhc_comp_add_local_svr_note*)
    public static final int QHC_CMD_COMP_DELETE_NOTE = 147;//企业删除相关通知					--回调data, 数据类型为(qhc_comp_delete_note*)
    public static final int QHC_CMD_COMP_STATUS_NOTE = 148;//企业状态相关通知
    public static final int QHC_CMD_COMP_SVR_USEABLE_RSP = 149;//前台服务器是否可用
    public static final int QHC_CMD_COMP_DEV_USEABLE_RSP = 150;//机器人是否可用
    public static final int QHC_CMD_COMP_JOIN_NOTE = 151;//被添加至企业通知
    public static final int QHC_CMD_COMP_MODIFY_DEV_NAME_RSP = 155;//修改团队机器人名称
    public static final int QHC_CMD_COMP_MODIFY_DEV_NAME_NOTE = 156;//修改团队机器人名称通知
    public static final int  QHC_CMD_CHANGE_SVR_RSP							=157;//更换前台服务器应答					--回调错误码
    public static final int  QHC_CMD_COMP_CHANGE_SVR_NOTE					=158;//更换前台服务器通知					--回调data, 数据类型为(qhc_comp_change_svr_note*)

    public static final int QHC_CMD_CHECK_ACCOUNT_PASSWORD_RSP = 153;//验证账户密码
    public static final int QHC_CMD_GET_ASSOCIATED_TEL_IDENTIFY_RSP = 59;//获取重新绑定验证码
    public static final int QHC_CMD_MODIFY_BINDING_MODE_RSP = 154;//重新绑定手机号

    public static final int  QHC_CMD_QUERY_COMPANY_SEMANTIC_STATUS_RSP = 161;//获取公司语义激活状态应答			--回调错误码/data, 数据类型为(qhc_company_status_info*)
    public static final int  QHC_CMD_SET_COMPANY_SEMANTIC_STATUS_RSP = 162;//设置公司语义激活状态应答			--回调错误码
    public static final int  QHC_CMD_QUERY_COMPANY_SEMANTICS_RSP = 163;//获取公司语义应答					--回调错误码/data, 数据类型为(qhc_company_semantics_info*)
    public static final int  QHC_CMD_ADD_COMPANY_SEMANTICS_RSP = 164;//新增公司语义应答					--回调错误码/data(语义index), 数据类型为(int*)
    public static final int  QHC_CMD_DEL_COMPANY_SEMANTICS_RSP = 165;//删除公司语义应答					--回调错误码/data,数据类型为(qhc_del_error_list*)
    public static final int  QHC_CMD_MODIFY_COMPANY_SEMANTICS_RSP = 166;//修改公司语义应答					--回调错误码
    public static final int  QHC_CMD_QUERY_COMPANY_SEMANTIC_COUNT_RSP =  167;//查询公司语义总数					--回调错误码/data,数据类型为(uint32_t*)
    
    public static final int QLINK_ADD_FRIEND_INFO = 100;//设置账号字段
    public static final int QHC_CMD_DOWNLOAD_DEV_FILE_RSP = 98;//管理员开始下载设备私人文件应答		--回调错误码/data,数据类型为(qhc_file_download_rsp_t*)
    public static final int QHC_CMD_FINISH_DOWNLOAD_DEV_FILE_RSP = 99;//管理员结束下载设备私人文件应答		--回调错误码
    public static final int QLINK_GET_VIDEO_SESSION = 0x00100900;//

    public static final int COMMON_ERROR_CODE_INPUT_CHECK_APP_ID = 1001; // app_id       输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_TEL = 1002; // tel          输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_IMEI = 1003; // imei         输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_UDID = 1004; // udid         输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_SEX = 1005; // sex          输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_HEIGHT = 1006; // height       输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_WEIGHT = 1007; // weight       输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_TYPE = 1008; // type         输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_ACCOUNT = 1009; // account      输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_PASS_WORD = 1010; // pass_word    输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_IDENTIFYCODE = 1011; // identifycode 输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_MAIL = 1012; // mail         输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_ALIAS = 1013; // alias        输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_BIRTHDAY = 1014; // birthday     输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_QUESTION1 = 1015; // question1    输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_ANSWER1 = 1016; // answer1      输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_QUESTION2 = 1017; // question2    输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_ANSWER2 = 1018; // answer2      输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_QUESTION3 = 1019; // question3    输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_ANSWER3 = 1020; // answer3      输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_WEIBO_ID = 1021; // weibo_id     输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_QQ_ID = 1022; // qq_id        输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_WEIXIN_ID = 1023; // weixin_id    输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_MODIFY_TP_TYPE = 1024; // modify tp type          输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_MODIFY_TP_MODIFYTYPE = 1025; // modify tp modifytype    输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_MODIFY_TP_QUERYTYPE = 1026; // modify tp modifytype    输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_UID = 1027; // uid          输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_STATUS = 1028; // status       输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_TOKEN = 1029; // token        输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_REMARKS = 1030; // remarks      输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_DSC = 1031; // remarks      输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_ANSWER_TYPE = 1032; // answer_type  输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_MSG_ID = 1033; // msg_id       输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_GROUP_NAME = 1034; // group name   输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_GROUP_ID = 1035; // group id     输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_GROUP_ID_LIST_TYPE = 1036; // group id list输入错误
    public static final int COMMON_ERROR_CODE_INPUT_CHECK_DEV_LOGIN_TYPE = 1037; // dev login type输入错误
    public static final int COMMON_ERROR_CODE_USERNAME_EXIST = 60011; //
    public static final int COMMON_ERROR_CODE_MOBILE_EXIST = 60012; //
    public static final int COMMON_ERROR_UPLOAD_FILE = 60013; //
    public static final int COMMON_CLIENT_ONLINE = 60014; //
    public static final int COMMON_CLIENT_OFFLINE = 60015; //
    public static final int QLINK_FRIEND_ADD_MYSELF_ERROR = 70000; //

    public static final int QLINK_ACCOUNT_SVR_NOT_FOUND_USR = 201001; // 找不到用户
    public static final int QLINK_ACCOUNT_SVR_UID_NOT_IN_RANGE = 201002; // UID不在支持范围
    public static final int QLINK_ACCOUNT_SVR_DATA_SVR_FAIL = 201003; // 数据层失败
    public static final int QLINK_ACCOUNT_SVR_USR_NOT_LOGIN = 201004; // 未登录不允许查询用户状态
    public static final int QLINK_ACCOUNT_SVR_NOT_SUPPORT_TYPE = 201005; // 类型不支持
    public static final int QLINK_ACCOUNT_SVR_PWD_NOT_MATCH = 201006; // 密码不匹配
    public static final int QLINK_ACCOUNT_SVR_TOKEN_NOT_MATCH = 201007; // token不匹配
    public static final int QLINK_ACCOUNT_SVR_CALL_ILLEGAL = 201008; // 非法调用
    public static final int QLINK_ACCOUNT_SVR_UPDATE_STATUS_FAIL = 201009; // 状态更新失败
    public static final int QLINK_ACCOUNT_SVR_SEND_LOGIN_SMS_FAIL = 201010; // 发送登录验证码失败
    public static final int QLINK_ACCOUNT_SVR_IDENTIFY_CODE_FAIL = 201011; // 从存储层查找验证码失败
    public static final int QLINK_ACCOUNT_SVR_IDENTIFY_CODE_NOT_FOUND_OR_OUT_OF_DATE_FAIL = 201012; // 验证码不匹配或者过期失败
    public static final int QLINK_ACCOUNT_SVR_GET_USER_INFO_FAIL = 201013; // 获取用户信息失败 需要帐号密码方式先登录
    public static final int QLINK_ACCOUNT_SVR_IMEI_OR_UDID_ERROR = 201014; // IMEI或者UDID相关错误
    public static final int QLINK_ACCOUNT_SVR_IDENTIFY_CODE_LOGIN_FIRST = 201015; // 不能以[帐号密码验证码]方式首次登录
    public static final int QLINK_ACCOUNT_SVR_IMEI_OR_UDID_NOT_MATCH = 201016; // 如果其他设备已经登录，只能用[帐号密码验证码方式]登录
    public static final int QLINK_ACCOUNT_SVR_RECORD_ADD_ASK_MSG_FAIL = 201017; // 记录【增加好友】消息失败
    public static final int QLINK_ACCOUNT_SVR_ADD_FRIEND_ANSWER_TYPE_ERROR = 201018; // 【增加好友】应答类型错误
    public static final int QLINK_ACCOUNT_SVR_DELETE_FRIEND_FAIL = 201019; // 删除好友失败
    public static final int QLINK_ACCOUNT_SVR_UID_QUERY_FAIL = 201020; // 根据帐号查询uid失败
    public static final int QLINK_ACCOUNT_SVR_FRIEND_REMARKS_WRITE_FAIL = 201021; // 修改好友备注失败
    public static final int QLINK_ACCOUNT_SVR_GROUP_CREATE_FAIL = 201022; // 创建群组失败
    public static final int QLINK_ACCOUNT_SVR_GROUP_UPDATE_FAIL = 201023; // 更新群组失败
    public static final int QLINK_ACCOUNT_SVR_GROUP_DELETE_FAIL = 201024; // 删除群组失败
    public static final int QLINK_ACCOUNT_SVR_GROUP_MEMBER_INSERT_FAIL = 201025; // 群组成员添加失败
    public static final int QLINK_ACCOUNT_SVR_GROUP_MEMBER_QUERY_FAIL = 201026; // 群组成员查询失败
    public static final int QLINK_ACCOUNT_SVR_GROUP_MEMBER_DELETE_FAIL = 201027; // 群组成员删除失败
    public static final int QLINK_ACCOUNT_SVR_GROUP_MEMBER_CHECK_FAIL = 201028; // 群组成员判断失败
    public static final int QLINK_ACCOUNT_SVR_GROUP_MEMBER_CHECK_NO = 201029; // 群组成员判断 非成员
    public static final int QLINK_ACCOUNT_SVR_GROUP_SET_ADMIN_FAIL = 201030; // 群组管理员设置失败
    public static final int QLINK_ACCOUNT_SVR_NOT_HAVE_DEV_MANAGER_FAIL = 201051; // 设备管理者不存在
    public static final int QLINK_ACCOUNT_SVR_DEV_MANAGER_DELETE_FAIL = 201052; // 管理者，不允许删除
    public static final int QLINK_ACCOUNT_SVR_NOT_DEV_MANAGER_FAIL = 201053; // 不是设备管理者
    public static final int QLINK_ACCOUNT_SVR_NOT_FRIEND_RELATION = 201050; // 不是好友关系
    public static final int QLINK_ACCOUNT_SVR_GROUP_MEMBER_ALREADY_BE = 201075;//已经是群成员
    public static final int ACCOUNT_SVR_APP_LOGIN_ERROR_COUNT_MAX = 201076; // app登录失败次数太多。请验证码登录

    public static final int ACCOUNT_SVR_ASK_UID_EQ_ANSWER_UID_ERROR = 201035; // 请求和应答的uid相同
    public static final int ACCOUNT_SVR_SEND_LOGIN_SMS_COUNT_LIMIT_FAIL = 201036; // 发送登录验证码数量超过限制次数失败(5次)
    public static final int ACCOUNT_SVR_FRIEND_RELATIONSHIP_ADD_FAIL = 201037; // 好友关系添加失败
    public static final int ACCOUNT_SVR_FRIEND_RELATIONSHIP_ADD_ANSWER_QUERY_FAIL = 201038; // 好友关系查询失败
    public static final int ACCOUNT_SVR_FRIEND_RELATIONSHIP_ADD_ANSWER_DEL_FAIL = 201039; // 好友关系删除失败
    public static final int ACCOUNT_SVR_TEL_NOT_MATCH = 201042;  // 输入的手机号与账户的手机号不一致
    public static final int ACCOUNT_SVR_PERMISSION_INVALID = 201049;  // 权限设置不正确
    public static final int LINK_ACCOUNT_SVR_GROUP_NOT_FRIEND_RELATION = 201060; // 不能添加非好友到群交
    public static final int ACCOUNT_SVR_NOT_GROUP_MANAGER_ERROR = 201061; // 不是设备管理者
    public static final int ACCOUNT_SVR_ACCOUNT_SAME_TO_PASSWD_ERROR = 201067; // 账户与密码不能相同
    // public static final int ACCOUNT_SVR_FRIEND_RELATIONSHIP_ALREADY_YES = 201073; // 已经是好友关系
    public static final int ACCOUNT_SVR_PC_QRCODE_ERROR = 201073; // 二维码错误
    public static final int ACCOUNT_SVR_FRIEND_NUM_TOO_MANY = 215004; // 对方好友数超过限制
    public static final int DATA_USER_ASK_NUM_OVER_LIMIL = 303006;//加好友请求的数目超过限制
    public static final int QLINK_ACCOUNT_SVR_ERROR_CODE_MAX = 202000;
    // 注册服务器
    public static final int REGISTER_SVR_ERROR_CODE_MIN = 202000;

    public static final int REGISTER_SVR_ALL_READY_REGISTER = 202001;
    public static final int REGISTER_SVR_SEND_SMS_FAIL = 202002;
    public static final int REGISTER_SVR_IDENTIFY_CODE_NOT_FOUND_OR_OUT_OF_DATE_FAIL = 202003; // 验证码不匹配或者过期失败
    public static final int REGISTER_SVR_ALREADY_REGISTER = 202004; // 已经注册
    public static final int REGISTER_SVR_UNKOWN_ERROR = 202005; // 注册时未知错误
    public static final int REGISTER_SVR_WRITE_QLINK_ACCOUNT_ERROR = 202006;// 注册时写入帐号信息错误
    public static final int REGISTER_SVR_MODIFY_ACCOUNT_NOT_FOUND_ERROR = 202007; // 帐号修改用户不存在失败
    public static final int REGISTER_SVR_MODIFY_ACCOUNT_PWD_OR_ACCOUNT_ERROR = 202008; // 帐号修改密码或者帐号不匹配
    public static final int REGISTER_SVR_CHECK_ACCOUNT_NOT_FOUND_ERROR = 202009; // 帐号检测不存在
    public static final int REGISTER_SVR_REIGSTER_SMS_CHECK_NOT_FOUND = 202010; // 注册验证码检测不存在
    public static final int REGISTER_EMAIL_HAS_REGISTERED = 202017; // 邮箱已被注册
    public static final int CTRLSVR_ERROR_BUSY = 105001;//服务器忙
    public static final int CTRLSVR_ERROR_PROTOBUF = 105002;//control_server解析protobuf失败
    public static final int CTRLSVR_ERROR_MSG_HEAD = 105003;//   control_server消息头出错
    public static final int CTRLSVR_ERROR_MSG_TYPE = 105004;//   control_server消息类型出错
    public static final int CTRLSVR_ERROR_AUTH_RELATION_SEND = 105005;//   control_server发送验证好友关系请求出错
    public static final int CTRLSVR_ERROR_AUTH_RELATION_TIMEOUT = 105006;//   control_server等待验证好友关系回应超时
    public static final int CTRLSVR_ERROR_AUTH_RELATION_PROTO = 105007;//   control_server解析验证好友关系回应的protobuf失败
    public static final int CTRLSVR_ERROR_AUTH_RELATION_FAIL = 105008;//   control_server验证好友关系失败（即不是好友关系）
    public static final int CTRLSVR_ERROR_GET_ONLINE_SEND = 105009;//   control_server发送在线请求出错
    public static final int CTRLSVR_ERROR_GET_ONLINE_TIMEOUT = 105010;//   control_server等待在线回应超时
    public static final int CTRLSVR_ERROR_GET_ONLINE_PROTO = 105011;//   control_server解析在线回应的protobuf出错

    public static final int REMOTE_DEVICE_NOT_ONLINE = 105012;//设备掉线
    public static final int REMOTE_DEVICE_INSERT_RECORD = 105014;//插入历史记录失败
    public static final int REMOTE_DEVICE_RESPONSE_TIMEOUT = 105016;//机器人回复超时

    public static final int IDENTIFY_RESPONSE_TIMEOUT = 28001;//获取验证码超时

    public static final int MSGSVR_ERROR_BUSY = 103001;//   message_server忙
    public static final int MSGSVR_ERROR_AUTH_RELATION_FAIL = 103017;
    public static final int NET_LIB_LOCAL_OFFLINE = 410004;// 掉线
    public static final int NET_LIB_LOCAL_THREAD_ERROR = 410003;// 启动线程失败
    public static final int NET_LIB_LOCAL_MEMORY_ERROR = 410002;// 申请内存失败
    public static final int NET_LIB_LOCAL_PROTOBUF_ERROR = 410001;// PROTOBUF转换错误
    public static final int NET_LIB_LOCAL_SELECT_TIMEOUT = 410010;// 掉线
    public static final int NET_LIB_DISCONNECT_RESPONSE = 410014;// 掉线
    public static final int LOCAL_OPEN_FILE_FAILED = 510001;    //打开文件失败
    public static final int LOCAL_NAME_SAME_TO_PWD_FAILED = 510002;    //打开文件失败
    public static final int VIDEO_CONNECT_OCCUPIED = 510003;//超过最大连接数
    public static final int NET_LIB_LOCAL_BUSY = 410008;//本地网络库忙
    public static final int COMMON_ERROR_NEED_TO_UPGRADE = 2001; //提示给客户 客户端必须升级
    public static final int COMMON_ERROR_FUNCTION_NOT_AVAILABLE = 2002; //某些不要紧的功能不可用使用
    public static final int SPDSVR_ERROR_USER_NOSPD = 107029; //不是附近的人
    public static final int SPDSVR_ERROR_USER_OFFLINE = 107018; //对方不在线
    public static final int SPDSVR_ERROR_NEARBY_CLOSE = 107023; //附近的人功能关闭

    public static final int DATA_SVR_UID_ERROR = 307004;//找不到UID

    // group_chat
    public static final int GROUP_CHAT_ERROR_MIN = 217001;
    public static final int GROUP_CHAT_ERROR_INPUT_COUNT = 217002; // 输入个数有误
    public static final int GROUP_CHAT_ERROR_NOT_GROUP_ADMIN = 217003; // 不是组管理者
    public static final int GROUP_CHAT_ERROR_GROUP_CNT_MAX = 217004; // 群组人员超过最大数量
    public static final int GROUP_CHAT_ERROR_NOT_GROUP_MEMBER = 217005; // 不是群组成员
    public static final int GROUP_CHAT_ERROR_READY_GROUP_MEMBER = 217006;// 是群组成员
    public static final int GROUP_CHAT_ERROR_SAVE_GROUP_ID = 217007; // 保存群组失败
    public static final int GROUP_CHAT_ERROR_DEL_GROUP_ID = 217008; // 删除群组失败
    public static final int GROUP_CHAT_ERROR_SAVE_GROUP_ID_MAX = 217009; // 群组保存超过最大保存数量
    public static final int GROUP_CHAT_ERROR_NOT_FIND_QRCODE = 217010; // 二维码错误(没有该二维码)
    public static final int GROUP_CHAT_ERROR_SYSTEM_BUSY = 217011; // 系统繁忙，稍后重试
    public static final int GROUP_CHAT_ERROR_REMARK_NULL = 307151; // 系统繁忙，稍后重试
    public static final int GROUP_CHAT_ERROR_CREATE_NOT_FRI = 217012;// 因为创建时存在非好友用户，导致创建不成功

    public final static int LIVE_MUSIC_PLAY = 0x00100400;//播放
    public final static int LIVE_MUSIC_POS_SET = 0x00100401;//改变进度
    public final static int LIVE_MUSIC_POS_GET = 0x00100402;//获取进度
    public final static int LIVE_MUSIC_PAUSE = 0x00100403;//暂停
    public final static int LIVE_MUSIC_PRE = 0x00100404;//上一首
    public final static int LIVE_MUSIC_NEXT = 0x00100405;//下一首
    public final static int LIVE_MUSIC_STOP = 0x00100406;//停止播放


    public static final int SETTING_AP_PASSWORD = 0x0010110c;// 修改ap密码
    public static final int SHOW_AP_PASSWORD = 0x0010110d;//获取AP密码
    public static final int SETTING_WIFI_PASSWORD = 0x0010110e;//设置WIFI参数

    public static final int SETTING_OPEN_VIDEO = 0x00101110;//开启视频参数
    public static final int GETTING_OPEN_VIDEO = 0x00101111;//获取开启视频参数

    public static final int SETTING_AUTO_SLEEP = 0x00101112;//自动休眠参数
    public static final int GETTING_AUTO_SLEEP = 0x00101113;//获取自动休眠参数

    public static final int SETTING_AUTO_CHECKTIME = 0x00101114;//自动校验时间参数
    public static final int GETTING_AUTO_CHECKTIME = 0x00101115;//获取自动校验时间参数
    public final static int REMOTE_CONTROL_CMD = 0x00700102;  //远程语音


    public final static int GETTING_PERMISSION_GROUPS = 0x00100819;  //获取权限组列表
    public final static int GETTING_GROUP_PERMISSIONS = 0x0010081a;  //获取权限组对应权限
    public final static int ADD_PERMISSION_GROUP = 0x0010081b;  //添加权限组
    public final static int EDIT_PERMISSION_GROUP = 0x0010081c;  //修改权限组
    public final static int DELETE_PERMISSION_GROUP = 0x0010081d;  //删除权限组
    public final static int GETTING_ALL_APP_PERMISSIONS = 0x0010081e;  //获取所有APP名称（权限）


    public final static int OK = 20000;

    //1. 与注册相关的错误码
    public final static int REGISTER_ERROR_ACCOUNT_EXISTS = 20001;
    public final static int REGISTER_ERROR_TEL_EXISTS = 20002;
    public final static int REGISTER_ERROR_PASSWORD_NOT_SAME = 20003;
    public final static int REGISTER_ERROR_PARAM_ERROR = 20004;
    public final static int REGISTER_ERROR_CONNECT_TO_SMS_SERVRE_TIMEOUT = 20005;
    public final static int REGISTER_ERROR_SEND_SMS_TO_SERVRE_TIMEOUT = 20006;
    public final static int REGISTER_ERROR_BEYOUND_REGISTER_ABILITY = 20007;
    public final static int REGISTER_ERROR_OPEN_TCP_CONN_FAILED = 20008;
    public final static int REGISTER_ERROR_CREATE_SMS_MSG_FAILED = 20009;
    public final static int REGISTER_ERROR_SEND_SMS_FAILED = 20010;
    public final static int REGISTER_ERROR_GET_IDENTIFY_CODE_FAILED = 20011;
    public final static int REGISTER_ERROR_SEND_SMS_TO_SMS_FAILED = 20012;
    public final static int REGISTER_ERROR_IDENTIFY_CODE_NOT_MATCH = 20013;
    public final static int REGISTER_ERROR_SET_TIMEOUT_FAILED = 20014;
    public final static int REGISTER_ERROR_IDENTIFY_IS_EXPIRED = 20015;
    public final static int REGISTER_ERROR_REGISTER_FAILED = 20016;
    public final static int REGISTER_ERROR_PROTOBUF_ERROR = 20017;

    //2. 与获取验证码相关的错误码
    public final static int SMS_ERROR_MORE_THAN_TIMES = 21001;
    public final static int SMS_ERROR_TEL_FORMAT_ERROR = 21002;
    public final static int SMS_ERROR_INTERNAL_ERROR = 21003;

    //3. 与登录相关的错误码
    public final static int LOGIN_ERROR_PARAM_ERROR = 22001;
    public final static int LOGIN_ERROR_ACCOUNT_NOT_FOUND = 22002;
    public final static int LOGIN_ERROR_PASSWORD_IS_NOT_CORRECT = 22003;

    //4. 与redis相关的错误码
    public final static int REDIS_ERROR_GET_DATA_FAILED = 23001;
    public final static int REDIS_ERROR_SAVE_DATA_FAILED = 23002;

    //5. 与获取用户信息相关的错误码
    public final static int GET_ERROR_USER_NOT_FOUND = 24001;

    //6. auth
    public final static int AUTH_ERROR_PARAM_ERROR = 25001;
    public final static int AUTH_ERROR_AUTH_FAILED = 25002;

    //7
    public final static int SELECT_ERROR_GET_HTTP_PROXY_FAILED = 26001;

    //8
    public final static int INSUFFICIENT_CONTENT_RESOURCES = 27001;

    //APP统计信息-页面代码
    public final static int FROM_LOGIN_PAGE = 0x001; //登录页
    public final static int FROM_INDEX_PAGE = 0x002; //首页
    public final static int FROM_SMARTLIFE_PAGE = 0x003; //智生活
    public final static int FROM_SMARTCIRCLE_PAGE = 0x004; //智能圈
    public final static int FROM_WODE_PAGE = 0x005; //我的
    public final static int FROM_SANBOT_EYE_PAGE = 0x006; //机器人的眼
    public final static int FROM_SCAN_BARCODE_PAGE = 0x007; //扫描二维码
    public final static int FROM_SCAN_MY_DEVICE_PAGE = 0x008; //我的设备
    public final static int FROM_DEVICE_DETAIL_PAGE = 0x009; //设备详情
    public final static int FROM_MY_ALBUM_PAGE = 0x00a; //我的相册
    public final static int FROM_MY_FRIEND_PAGE = 0x00b; //我的好友
    public final static int FROM_FRIEND_DETAIL_PAGE = 0x00c; //联系人详情
    public final static int FROM_PERMISSION_MANAGEMENT_PAGE = 0x00d; //权限管理
    public final static int FROM_PERMISSION_INFO_PAGE = 0x00e; //权限信息

    public final static int FROM_SETTING_PAGE = 0x00f; //设置页面
    public final static int FROM_ACCOUNT_SETTING_PAGE = 0x010; //账号设置
    public final static int FROM_CHATING_PAGE = 0x011; //聊天界面
    public final static int FROM_CHATING_MSG_PAGE = 0x012; //聊天信息
    public final static int FROM_MESSAGE_PAGE = 0x013; //信息页面
    public final static int FROM_SMART_VOICE_PAGE = 0x014; //智能语音
    public final static int FROM_REMOTE_VOICE_PAGE = 0x015; //远程语音
    public final static int FROM_MUSIC_PAGE = 0x016; //音乐
    public final static int FROM_DANCE_PAGE = 0x017; //跳舞
    public final static int FROM_MOVIE_PAGE = 0x018; //电影
    public final static int FROM_PROJECTER_PAGE = 0x019; //投影仪
    public final static int FROM_EXCHANGE_MENU_PAGE = 0x01a; //切换菜单
    public final static int FROM_SCHEDULE_PAGE = 0x01b; //日程
    public final static int FROM_CREATE_SCHEDULE_PAGE = 0x01c; //新建日程

    public final static int FROM_PERMISSION_EDIT_PAGE = 0x01d; //编辑权限
    public final static int FROM_SMALLHORN_PAGE = 0x01e; //小喇叭
    public final static int FROM_DEVICEFRIEND_PAGE = 0x01f; //设备好友
    public final static int FROM_FREEWALK_PAGE = 0x020; //自由行走
    public final static int FROM_CATCHDUCK_PAGE = 0x021; //赶鸭子
    public final static int FROM_XIMALAYA_PAGE = 0x022; //喜马拉雅
    public final static int FROM_APPMARKET_PAGE = 0x023; //应用市场
    public final static int FROM_REGISTER_FIRST_PAGE = 0x022; //注册第一页
    public final static int FROM_REGISTER_SECOND_PAGE = 0x023; //注册第二页
    public final static int FROM_REGISTER_PASSWORD_PAGE = 0x024; //注册密码页
    public final static int FROM_REGISTER_EMAIL_PAGE = 0x025; //注册邮箱页
    public final static int FROM_REGISTER_EMAIL_DONE_PAGE = 0x026; //注册邮箱完成页
    public final static int FROM_COUNTRY_PAGE = 0x027; //国家页
    public final static int FROM_FORGETFIRST_PAGE = 0x028; //忘记密码首页
    public final static int FROM_FORGETPHONE_PAGE = 0x029; //忘记密码手机页
    public final static int FROM_FORGETEMAIL_PAGE = 0x02a; //忘记密码邮箱
    public final static int FROM_SAFEHOME_PAGE = 0x02b; //安全家
    public final static int FROM_DEFENCE_PAGE = 0x02c; //布防策略
    public final static int FROM_INTRUDE_PAGE = 0x02d; //入侵
    public final static int FROM_CROSSBOUNDARY_PAGE = 0x02e; //越界
    public final static int FROM_TECHSUPPORT_PAGE = 0x02f; //技术支持
    public final static int FROM_SETTING_MORE_PAGE = 0x030; //更多设置
    public final static int FROM_TRANSFER_CODE_PAGE = 0x031; //转移权限验证码

    //APP统计信息-按键代码开始

    public final static int LOGIN_CODE = 0x00101;//登录             257
    public final static int FORGET_PWD_CODE = 0x00102;//忘记密码     258
    public final static int CREATE_ACCOUNT_CODE = 0x00103;//创建帐号  259
    public final static int JUST_LOOK_CODE = 0x00104;//随便看看       260
    public final static int CHOOSE_COUNTRY_CODE = 0x00105;//选择国家代号 261
    public final static int IF_SHOW_PASSWORD = 0x00106;//选择密码显示与否 262
    public final static int REGISTER_NEXT = 0x00107;//注册下一步 263
    public final static int SHOW_PROTOCOLTEXT = 0x00107;//显示用户须知 264
    public final static int REGISTER_SECOND_NEXT = 0x00108;//注册第二页下一步 265
    public final static int REGISTER_GETCODE = 0x00109;//获取验证码 266
    public final static int REGISTER_CHOOSE_COUNTRY = 0x0010a;//选择国家 267
    public final static int REGISTER_EMAIL = 0x0010b;//邮箱注册 268
    public final static int REGISTER_PASSWORD = 0x0010c;//设置密码 269
    public final static int REGISTER_EMAIL_NEXT = 0x0010d;//设置密码 270
    public final static int REGISTER_EMAIL_COUNTRY = 0x0010e;//设置密码 271
    public final static int REGISTER_EMAIL_DONE = 0x0010f;//设置密码 272
    public final static int COUNTRY = 0x00110;//设置国家 273
    public final static int FORGET_FIRST_NEXT = 0x00111;//忘记密码下一步 274
    public final static int FORGET_FIRST_COUNTRY = 0x00112;//忘记密码选择国家 275
    public final static int FORGET_PHONE_DONE = 0x00113;//忘记密码确定 276
    public final static int IS_SHOW_PASSWORD = 0x00114;//密码明暗文 277
    public final static int FORGET_PHONE_AUTH = 0x00115;//忘记密码验证码 277
    public final static int FORGET_EMAIL_DONE = 0x029; //忘记密码邮箱完成

    public final static int INFORMATION_CODE = 0x00201;//信息         513
    public final static int SMARTLIFE_CODE = 0x00202;//智生活          514
    public final static int SMARTCIRCLE_CODE = 0x00203;//智能圈        515
    public final static int NY_CODE = 0x00204;//我的                  516
    public final static int AUDIO_CODE = 0x00205;//语音                517
    public final static int SANBOT_EYE_CODE = 0x00206;//机器人的眼       518
    public final static int MORE_CODE = 0x00207;//更多                    519


    public final static int ADDRESS_BOOK_CODE = 0x00301;//通讯录
    public final static int MUSIC_CODE = 0x00302;//音乐
    public final static int MOVIE_CODE = 0x00303;//电影
    public final static int DANCE_CODE = 0x00304;//跳舞
    public final static int SCHEDULE_CODE = 0x00305;//日程
    public final static int PROJECTER_CODE = 0x00306;//投影仪
    public final static int TALKSHOW_CODE = 0x00307;//相声
    public final static int AUDIO_PROMPT_CODE = 0x00308;//语音提示
    public final static int LISTEN_STORY_CODE = 0x00309;//听小说
    public final static int STORY_MORE_CODE = 0x0030a;//听小说更多
    public final static int ENTERTAINMENT_CODE = 0x0030b;//听娱乐
    public final static int ENTERTAINMENT_MORE_CODE = 0x0030c;//听娱乐更多
    public final static int LISTEN_TALKSHOW_CODE = 0x0030d;//听相声
    public final static int TALKSHOW_MORE_CODE = 0x0030e;//听相声更多
    public final static int LISTEN_MUSIC_CODE = 0x0030f;//听音乐
    public final static int MUSIC_MORE_CODE = 0x00310;//听音乐更多
    public final static int LISTEN_HISTORY_CODE = 0x00311;//听历史
    public final static int HISTORY_MORE_CODE = 0x00312;//听历史更多
    public final static int LISTEN_BOARDCAST_CODE = 0x00313;//听广播
    public final static int BOARDCAST_MORE_CODE = 0x00314;//听广播更多
    public final static int LISTEN_CHILDREN_CODE = 0x00315;//听儿童
    public final static int CHILDREN_MORE_CODE = 0x00316;//听儿童更多
    public final static int LISTEN_TRAVLING_CODE = 0x00317;//听旅游
    public final static int TRAVLING_MORE_CODE = 0x00318;//听旅游更多


    public final static int ADD_DEVICE_CODE = 0x00401;//添加外设                 // TODO:HERE
    public final static int OPEN_SANBOT_EYE_CODE = 0x00402;//打开机器人的眼


    public final static int HEAD_PHOTO_CODE_1 = 0x00501;//头像
    public final static int HEAD_PHOTO_CODE_2 = 0x00502;//帐号信息
    public final static int HEAD_PHOTO_CODE_3 = 0x00503;//我的相册
    public final static int HEAD_PHOTO_CODE_4 = 0x00504;//我的好友
    public final static int HEAD_PHOTO_CODE_5 = 0x00505;//权限管理
    public final static int HEAD_PHOTO_CODE_6 = 0x00506;//系统设置
    public final static int HEAD_PHOTO_CODE_7 = 0x00507;//我的设备
    public final static int HEAD_PHOTO_CODE_8 = 0x00508;//二维码名片
    public final static int HEAD_PHOTO_CODE_9 = 0x00509;//智能社区
    public final static int HEAD_PHOTO_CODE_10 = 0x0050a;//技术支持
    public final static int HEAD_PHOTO_CODE_11 = 0x0050b;//技术支持done


    public final static int AUDIO_CODE_1 = 0x00601;//语音
    public final static int RECORD_CODE = 0x00602;//录像
    public final static int SCREESHOT_CODE = 0x00603;//截图
    public final static int HEAD_CONTROL_CODE = 0x00604;//头部控制
    public final static int LEFTHAND_CONTROL_CODE = 0x00605;//左手控制
    public final static int RIGHTHAND_CONTROL_CODE = 0x00606;//右手控制
    public final static int FOOT_CONTROL_CODE = 0x00607;//脚部控制
    public final static int EMOTION_BUTTON_CODE = 0x00608;//表情按钮
    public final static int SEND_BUTTON_CODE = 0x00609;//发送按钮
    public final static int CLEAR_BUTTON_CODE = 0x0060a;//清除按钮
    public final static int BACK_BUTTON_CODE = 0x0060b;//返回按钮
    public final static int AUDIO_EXCHANGE_BUTTON_CODE = 0x0060c;//语音转换按钮
    public final static int KEYBOARD_AUDIO_EXCHANGE_CODE = 0x0060d;//键盘语音切换按钮
    public final static int BACK_CODE = 0x0060e;//返回


    public final static int ADD_FRIEND_CODE_1 = 0x00701;//添加好友
    public final static int ADD_DEVICE_CODE_1 = 0x00702;//添加外设
    public final static int ADD_BACK_CODE_1 = 0x00703;//返回


    public final static int SCAN_BARCODE_CODE = 0x00801;//扫描二维码
    public final static int DELETE_DEVICE_CODE = 0x00802;//删除设备
    public final static int ADELETE_OUTSIDE_DEVICE_CODE = 0x00803;//删除外设
    public final static int BARCODE_BACK_CODE = 0x00804;//返回


    public final static int EDIT_BUTTON_CODE = 0x00901;//修改按钮
    public final static int EDIT_BACK_CODE = 0x00902;//返回

    public final static int XIANGCE_CHOSE_ALL_CODE = 0x00a01;//全选
    public final static int XIANGCE_DELETE_CODE = 0x00a02;//删除
    public final static int XIANGCE_CANCEL_CODE = 0x00a03;//取消
    public final static int XIANGCE_BACK_CODE = 0x00a04;//返回


    public final static int ADDRESS_SEARCH_CODE = 0x00b01;//搜索
    public final static int ADDRESS_SCAN_CODE = 0x00b02;//扫描二维码
    public final static int ADDRESS_ADD_FRIEND_CODE = 0x00b03;//添加好友
    public final static int ADDRESS_DELETE_FRIEND_CODE = 0x00b04;//删除好友
    public final static int ADDRESS_BACK2_CODE = 0x00b05;//返回

    public final static int FRIEND_DETAIL_HEADPHOTO_BUTTON = 0x00c01;//头像按钮
    public final static int FRIEND_DETAIL_SEND_MSG = 0x00c02;//发送信息
    public final static int FRIEND_DETAIL_MORE = 0x00c03;//更多
    public final static int FRIEND_DETAIL_BACK = 0x00c05;//返回


    public final static int PERMISSION_CHOOSE_ROBOT_BACK = 0x00d01;//选择机器人
    public final static int PERMISSION_ADD_GROUP = 0x00d02;//添加权限组
    public final static int PERMISSION_DISTRIBUTION = 0x00d03;//分配权限
    public final static int PERMISSION_LOOK = 0x00d04;//查看权限
    public final static int PERMISSION_BACK = 0x00d05;//返回
    public final static int PERMISSION_TRANSFORM = 0x00d06;//权限转让
    public final static int PERMISSION_ON = 0x00d07;//权限激活

    public final static int PERMISSION_INFO_1 = 0x00e01;//编辑权限
    public final static int PERMISSION_INFO_2 = 0x00e02;//返回
    public final static int PERMISSION_INFO_3 = 0x00e03;//管理员身份转让


    public final static int SETTING_PAGE_1 = 0x00f01;//省流量模式
    public final static int SETTING_PAGE_2 = 0x00f02;//自动打开视频
    public final static int SETTING_PAGE_3 = 0x00f03;//技术支持
    public final static int SETTING_PAGE_4 = 0x00f04;//关于Qlink
    public final static int SETTING_PAGE_5 = 0x00f05;//安全退出
    public final static int SETTING_PAGE_6 = 0x00f06;//返回
    public final static int SETTING_PAGE_7 = 0x00f07;//版本检测

    public final static int ACCOUT_SETTING_1 = 0x01001;//拍照上传
    public final static int ACCOUT_SETTING_2 = 0x01002;//本地相册
    public final static int ACCOUT_SETTING_3 = 0x01003;//修改昵称
    public final static int ACCOUT_SETTING_4 = 0x01004;//我的二维码
    public final static int ACCOUT_SETTING_5 = 0x01005;//修改密码
    public final static int ACCOUT_SETTING_6 = 0x01006;//返回
    public final static int ACCOUT_SETTING_7 = 0x01007;//头像大图

    public final static int CHATING_1 = 0x01101;//语音键盘切换
    public final static int CHATING_2 = 0x01102;//语音输入
    public final static int CHATING_3 = 0x01103;//发送图片
    public final static int CHATING_4 = 0x01104;//视频聊天
    public final static int CHATING_5 = 0x01105;//音频聊天
    public final static int CHATING_6 = 0x01106;//播放语音
    public final static int CHATING_7 = 0x01107;//查看图片
    public final static int CHATING_8 = 0x01108;//返回


    public final static int CHATING_INFO_1 = 0x01201;//设置备注
    public final static int CHATING_INFO_2 = 0x01202;//清空聊天记录
    public final static int CHATING_INFO_3 = 0x01203;//返回


    public final static int INFORMATION_1 = 0x01301;//删除聊天会话
    public final static int INFORMATION_2 = 0x01302;//同意添加好友
    public final static int INFORMATION_3 = 0x01303;//拒绝添加好友


    public final static int SMARTAUDIO_1 = 0x01401;//智能语音
    public final static int SMARTAUDIO_2 = 0x01402;//返回

    public final static int REOMTE_AUDIO_1 = 0x01501;//远程语音
    public final static int REOMTE_AUDIO_2 = 0x01502;//返回

    public final static int MUSIC_1 = 0x01601;//刷新按钮
    public final static int MUSIC_2 = 0x01602;//播放按钮
    public final static int MUSIC_3 = 0x01603;//上一首
    public final static int MUSIC_4 = 0x01604;//下一首
    public final static int MUSIC_5 = 0x01605;//停止
    public final static int MUSIC_6 = 0x01606;//进度条
    public final static int MUSIC_7 = 0x01607;//选择机器人
    public final static int MUSIC_8 = 0x01608;//点播放音乐
    public final static int MUSIC_9 = 0x01609;//返回


    public final static int DANCE_1 = 0x01701;//刷新按钮
    public final static int DANCE_2 = 0x01702;//播放按钮
    public final static int DANCE_3 = 0x01703;//上一首
    public final static int DANCE_4 = 0x01704;//下一首
    public final static int DANCE_5 = 0x01705;//停止
    public final static int DANCE_6 = 0x01706;//进度条
    public final static int DANCE_7 = 0x01707;//选择机器人
    public final static int DANCE_8 = 0x01708;//点播放音乐
    public final static int DANCE_9 = 0x01709;//返回

    public final static int MOVIE_1 = 0x01801;//刷新按钮
    public final static int MOVIE_2 = 0x01802;//播放按钮
    public final static int MOVIE_3 = 0x01803;//上一首
    public final static int MOVIE_4 = 0x01804;//下一首
    public final static int MOVIE_5 = 0x01805;//停止
    public final static int MOVIE_6 = 0x01806;//进度条
    public final static int MOVIE_7 = 0x01807;//选择机器人
    public final static int MOVIE_8 = 0x01808;//点播放音乐
    public final static int MOVIE_9 = 0x01809;//返回


    public final static int PROJECTER_1 = 0x01901;//刷新按钮
    public final static int PROJECTER_2 = 0x01902;//选择机器人
    public final static int PROJECTER_3 = 0x01903;//打开投影
    public final static int PROJECTER_4 = 0x01904;//自动打开
    public final static int PROJECTER_5 = 0x01905;//模式选择
    public final static int PROJECTER_6 = 0x01906;//高级设置
    public final static int PROJECTER_7 = 0x01907;//返回


    public final static int MENU_EXCHANGE_DANCE = 0x01a01;//跳舞
    public final static int MENU_EXCHANGE_MOVIE = 0x01a02;//电影
    public final static int MENU_EXCHANGE_PROJECTER = 0x01a03;//投影
    public final static int MENU_EXCHANGE_EYE = 0x01a04;//眼睛
    public final static int MENU_EXCHANGE_AUDIO = 0x01a05;//语音
    public final static int MENU_EXCHANGE_MUSIC = 0x01a06;//音乐


    public final static int SCHEDULE_1 = 0x01b01;//新建日程
    public final static int SCHEDULE_2 = 0x01b02;//选择机器人
    public final static int SCHEDULE_3 = 0x01b03;//上一月
    public final static int SCHEDULE_4 = 0x01b04;//下一月
    public final static int SCHEDULE_5 = 0x01b05;//选择年月
    public final static int SCHEDULE_6 = 0x01b06;//选择日期
    public final static int SCHEDULE_7 = 0x01b07;//返回


    public final static int CREATE_SCHEDULE_1 = 0x01c01;//选择日期
    public final static int CREATE_SCHEDULE_2 = 0x01c02;//语音提醒
    public final static int CREATE_SCHEDULE_3 = 0x01c03;//唱歌
    public final static int CREATE_SCHEDULE_4 = 0x01c04;//舞蹈
    public final static int CREATE_SCHEDULE_5 = 0x01c05;//时间选择
    public final static int CREATE_SCHEDULE_6 = 0x01c06;//仅一次
    public final static int CREATE_SCHEDULE_7 = 0x01c07;//每天重复
    public final static int CREATE_SCHEDULE_8 = 0x01c08;//每周
    public final static int CREATE_SCHEDULE_9 = 0x01c09;//每年重复
    public final static int CREATE_SCHEDULE_10 = 0x01c0a;//完成按钮
    public final static int CREATE_SCHEDULE_11 = 0x01c0b;//返回
    public final static int CREATE_SCHEDULE_12 = 0x01c0c;//自由行走
    public final static int CREATE_SCHEDULE_13 = 0x01c0d;//小喇叭
    public final static int CREATE_SCHEDULE_14 = 0x01c0e;//
    public final static int CREATE_SCHEDULE_15 = 0x01c0f;//

    public final static int PERMISSION_EDIT_1 = 0x01d01; //确定
    public final static int PERMISSION_EDIT_2 = 0x01d02; //返回

    //自由行走
    public final static int RECORD = 0x01d03; //录像
    public final static int FREEWALK = 0x01d04; //自由行走

    //小喇叭
    public final static int CREATE_NEW_HORN = 0x01e01;//创建小喇叭
    public final static int CREATE_NEW_TASK = 0x01e02;//创建任务
    public final static int PLAY_HORN_TASK = 0x01e03;//播放任务
    public final static int HORN_DETAIL = 0x01e04;//小喇叭详情
    public final static int HORN_DELETE = 0x01e05;//小喇叭删除

    //赶鸭子
    public final static int CATCH_DUCK = 0x02001;//赶鸭子

    //设备好友
    public final static int DEVICE_FRIEND = 0x03001; //设备好友

    //喜马拉雅

    public final static int XIMALAYA = 0x03002; //喜马拉雅
    //应用市场
    public final static int APP_TYPE_1 = 0x03003; //新品推荐
    public final static int APP_TYPE_2 = 0x03004; //社交通讯
    public final static int APP_TYPE_3 = 0x03005; //生活实用
    public final static int APP_TYPE_4 = 0x03006; //影音播放
    public final static int APP_TYPE_5 = 0x03007; //游戏娱乐
    public final static int APP_TYPE_6 = 0x03008; //资讯阅读
    public final static int APP_TYPE_7 = 0x03009; //旅游出行
    public final static int APP_TYPE_8 = 0x0300a; //办公学习
    public final static int APP_TYPE_9 = 0x0300b; //投资理财
    public final static int APP_TYPE_10 = 0x0300c; //系统工具
    public final static int GO_INTO_MARKET_INDEX = 0x0300d; //进入应用市场首页

    // 我的好友
    public final static int MY_FRIEND = 0x0300e; //设备好友

    //我的设备
    public final static int MY_DEVICE_DONE = 0x0300f; //设备好友
    public final static int DEVICE_MODIFY = 0x03010; //设备详情修改
    public final static int DEVICE_SURE = 0x03011; //设备详情确定
    public final static int DEVICE_BACK = 0x03012; //返回

    //已安装第三方app
    public final static int DOWNLOADED_INSTALLED_APPS = 0x04001; //已安装第三方app

    //安全家
    public final static int SELECT_ON = 0x05001; //安全家开关
    public final static int DEFENCE_CODE = 0x05002; //编辑策略按键
    public final static int INTRUDE_CODE = 0x05003; //入侵编辑
    public final static int CROSSBOUNDARY_CODE = 0x05004; //越界编辑
    public final static int INTRUDE_ON = 0x05005; //入侵开关
    public final static int CROSSBOUNDARY_ON = 0x05006; //越界开关
    public final static int BLOCK_ON = 0x05007; //遮挡开关
    public final static int ISVEDIOPHONE = 0x05008; //视频开关
    public final static int PHONE_SAVE = 0x05009; //存储按键
    public final static int VEDIO_SAVE = 0x0500a; //存储按键2
    public final static int VOICE = 0x0500b; //报警音
    public final static int INTRUDE_DONE = 0x0500c; //入侵
    public final static int CROSSBOUNDARY_DONE = 0x0500d; //越界
    public final static int ALARM_RECORD_IS_NULL = 213606; //无报警记录
    public final static int ALARM_PIC_VIDEO_NULL = 213406; //无记录


    //-----------------------------------------APP统计信息-按键代码结束--------------------------------------


    //小喇叭
    public final static int GET_HORN_RECORD = 0x0010081f; //获取小喇叭记录
    public final static int GET_FILE_LIST = 0x00100828; //获取文件列表
    public final static int ADD_HORN = 0x00100820; //添加小喇叭
    public final static int EDIT_HORN = 0x00100821; //修改小喇叭
    public final static int GET_HORN_BY_ID = 0x00100829; //通过id获取小喇叭详情
    public final static int DEL_HORN_BY_ID = 0x00100822; //通过id删除小喇叭
    public final static int PLAY_HORN = 0x00100823; //播放小喇叭
    public final static int PLAY_HORN_STATUS = 0x00100826; //获取播放小喇叭状态
    public final static int PLAY_HORN_MODEL = 0x00100827; //播放模式
    public final static int STOP_PLAY_HORN = 0x00100825; //停止播放
    public final static int PAUSE_PLAY_HORN = 0x00100824; //暂停播放

    //拍照控制
    public final static int OPEN_TAKING_PICTURES = 0x0010082a; //打开拍照
    public final static int CLOSE_TAKING_PICTURES = 0x0010082b;  //关闭拍照

    //赶鸭子
    public final static int OPEN_CATCH_THE_DUCK = 0x0010082c;  //开启赶鸭子
    public final static int CLOSE_CATCH_THE_DUCK = 0x0010082d;  //关闭赶鸭子
    public final static int CHECK_IS_OPEN_CATCH_THE_DUCK = 0x0010082e;  //查询是否开启赶鸭子

    //自由行走
    public final static int OPEN_FREE_WALK = 0x0010082f; //打开自由行走
    public final static int CLOSE_FREE_WALK = 0x00100830; //关闭自由行走
    public final static int CHECK_IS_OPEN_FREE_WALK = 0x00100831; //检查是否开启自由行走


    //查询机器人状态
    public final static int QUERY_ROBOT_STATUS = 0x00100832;//查询机器人状态
    //自由巡航

    public final static int OPEN_FREE_CRUISE = 0x00100833; //开启巡航
    public final static int CLOSE_FREE_CRUISE = 0x00100834; //关闭巡航
    public final static int GET_FREE_CRUISE_STATUS = 0x00100835; //查询是否开启巡航

    //拍照控制
    public final static int OPEN_THE_CAMERA = 0x0010082a; //打开相机拍照
    public final static int CLOSE_THE_CMACRA = 0x0010082b; //关闭相机拍照
    //文件上传
    public final static int FILE_SIZE_ERROR = 213082;    //文件大小错误


    //智生活
    public final static int GET_LOCAL_INSTALLED_AND_CONTROL_APP_LIST = 0x00100846; //机器人可远程操作的APP
    public final static int GET_THIRD_PARTH_INSTALLED_AND_CONTROL_APP_LIST = 0x00100847; //经下载安装第三方应用--获取已经安装并有权限控制APP列表
    public final static int OPEN_APP_FROM_ROBOT = 0x00100848; //打开机器人端app
    public final static int CLOSE_APP_FROM_ROBOT = 0x00100849; //关闭机器人端app
    public final static int GET_CRUUENT_OPEN_APP_FROM_ROBOT = 0x0010084a; //获取机器人端当前打开APP
    public final static int GET_MARKET_INIT_PARAM_FROM_ROBOT = 0x0010084b; //获取APP传递参数
    public final static int DOWNLOAD_APP_FROM_ROBOT = 0x0010084c; //下载app
    public final static int GET_APP_DOWNLOAD_STATUS_FROM_ROBOT = 0x0010084d; //获取app安装状态
    public final static int GET_CURRENT_APP_STATUS = 0x00100861;//获取当前打开的应用状态
    public final static int GET_ROBOT_PROTECT_INFO = 0x0010086d;//获取护眼参数
    public final static int SET_ROBOT_PROTECT_INFO = 0x0010086e;//设置护眼参数
    public final static int GET_ROBOT_APP_INFO = 0x00100871;//获应用使用参数
    public final static int GET_ROBOT_RECOMMAND_APP_INFO = 0x0010086f;//获取应用推荐参数
    public final static int SET_ROBOT_RECOMMAND_APP_INFO = 0x00100870;//设置应用推荐参数

    public final static int GET_HEALTH_MONITOR_INFO = 0x0020087c;//获取健康监测数据
    public final static int GET_MEDITION_INFO = 0x00200872;//获取用药提醒记录
    public final static int ADD_MEDITION_INFO = 0x00200873;//添加用药提醒记录
    public final static int SET_MEDITION_INFO = 0x00200874;//修改用药提醒记录
    public final static int DEL_MEDITION_INFO = 0x00200875;//删除用药提醒记录
    public final static int GET_MOTION_INFO = 0x00200876;//获取运动提醒记录
    public final static int SET_MOTION_INFO = 0x00200877;//修改运动提醒记录
    public final static int GET_REST_INFO = 0x00200878;//获取休息提醒记录
    public final static int SET_REST_INFO = 0x00200879;//修改休息提醒记录
    public final static int GET_LIVE_INFO = 0x0020087a;//获取生活提醒记录
    public final static int SET_LIVE_INFO = 0x0020087b;//修改生活提醒记录

    public final static int COMMON_ERROR_UID_IS_0 = 201047;

    //权限管理
    public static final int QLINK_COMM_CHECK_MGR_RSP = 52;//机器人管理者鉴权请求

    public static final int PTC_CMD_ROBOT_MOTION_STATUS = 0x00212004;//机器人状态


    /***
     *
     * 错误码
     1000;//没有找到充电桩
     1001;//正在充电
     1002; //不需要充电
     1003; //非寻找充电桩状态
     * */
    //控制机器人充电
    public final static int BEGIN_TO_CHARGE = 0x0010085a; //开始充电
    public final static int END_TO_CHARGE = 0x0010085b; //结束充电
    public final static int QUERY_CHARGE_STATUS = 0x0010085c; //查询充电状态
    public final static int NOT_FIND_CHARGE_PILE = 1000;
    public final static int IS_CHARGING = 1001;
    public final static int NO_NEED_TO_CHARGE = 1002;
    public final static int NOT_FINDING_CHARGE_PILE_STATUS = 1003;

    //前台接待
    public final static int TYPE_GET_RECEPTION_MAP_LIST = 0x0010085d;
    public final static int TYPE_GET_RECEPTION_ACCEPT = 0x0010085e;
    public final static int TYPE_GET_RECEPTION_REFUSE = 0x0010085f;
    public final static int TYPE_GET_RECEPTION_WAIT = 0x00100860;
    public final static int TYPE_GET_RECEPTION_MAP_NEW = 0x00100863;
    public final static int TYPE_SET_RECEPTION_MAP_NEW = 0x00100864;
    public final static int TYPE_GET_RECEPTION_VISITOR = 0x00100865;
    public final static int TYPE_SET_RECEPTION_VISITOR = 0x00100866;

    //企业组织错误码，部分沿用老错误码

    public final static int DATA_COMPANY_CREATE_PROBUF_PARSE_ERROR = 307365;  //反序列化错误
    public final static int DATA_CHECK_ROBOT_USE_EXIST = 307366;  //机器人已被使用
    public final static int DATA_COMPANY_NOT_EXIST = 307367;  //公司不存在
    public final static int DATA_ROBOT_LINK_COMPANY = 307368; //机器人已经和公司做关联
    public final static int DATA_ROBOT_NOT_LINK_COMPANY = 307369;  //机器人没有和公司做关联
    public final static int DATA_CHECK_ROBOT_NOT_USE = 307370; //机器人没有使用
    public final static int DATA_CHECK_DEPT_MAX_LEVEL = 307371;  //部门已到最大级别
    public final static int DATA_CHECK_DEPT_MAX_NUM = 307372;  //部门已到最大数量
    public final static int DATA_CHECK_MEMBER_NOT_EXIST = 307373;  //成员不存在
    public final static int DATA_CHECK_RABOT_MIN_NUM = 307374; //机器人数量小于2
    public final static int DATA_CHECK_DEPT_NOT_EXIST = 307375;  //部门不存在
    public final static int DATA_CHECK_DEPT_NOT_DOWNMOST = 307376; //部门下还有部门，删除失败
    public final static int DATA_CHECK_DEL_DEPT_NOT_ONE = 307377; //一次删除的部门大于1
    public final static int DATA_LINK_FACE_SVR_TYPE_ERROR = 307378; //关联类型错误
    public final static int DATA_CHECK_MEMBER_IS_EXIST = 307379; //成员已存在
    public final static int DATA_COMPANY_NAME_EXIST = 307380; //公司名称已存在
    public final static int DATA_COMPANY_OR_DEPT_ID_ERROR = 307381; //公司ID或部门ID错误


    public final static int COMPANY_ERROR_USER_NO_PERMISSION = 222002;// 用户无权限
    public final static int COMPANY_ERROR_USER_NOT_COMP_MEMBER = 222003; // 非成员
    public final static int COMPANY_ERROR_ROBOT_USEABLE = 222004; // 机器人已经被企业使用

    //服务报表
    public final static int SERVICE_REPORT_GET_ATTENDANCE_STATUS = 0x00100867; //获取考勤状况
    public final static int SERVICE_REPORT_GET_ATTENDANCE_LIST = 0x00100868; //获取考勤列表

    //访客报表
    public final static int VISITORS_REPORT_GET_VISITORS_STATUS = 0x00100869;//获取访客状况
    public final static int VISITORS_REPORT_GET_VISITORS_LIST = 0x0010086a;//获取访客列表

    //机器人组合动作
    public final static int ROBOT_ACTION_CMD = 0x00100862;//组合动作

}
