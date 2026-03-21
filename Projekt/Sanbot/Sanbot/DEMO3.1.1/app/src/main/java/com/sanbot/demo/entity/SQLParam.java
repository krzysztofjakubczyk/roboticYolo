package com.sanbot.demo.entity;

/**
 * 数据库参数
 *
 * @author youngbin
 */
public class SQLParam {

    //数据库名
    public static final String FILE_NAME = "qlink_%d.db";

    //好友表
    public class Friend {

        public static final String TABLE_NAME = "friend";

        public static final String UID = "uid";
        public static final String ACCOUNT = "account";
        public static final String TEL = "tel";
        public static final String NICKNAME = "nickname";
        public static final String REMARK = "remark";
        public static final String SEX = "sex";
        public static final String BIRTHDAY = "birthday";
        public static final String HEIGHT = "height";
        public static final String WEIGHT = "weight";
        public static final String EMAIL = "email";
        public static final String AVATAR_ID = "avatar_id";
        public static final String AVATAR_URL = "avatar_url";
        public static final String TYPE = "type";
        public static final String PERMISSION = "permission";
        public static final String REMARK_VERSION = "remark_version";
        public static final String BASEINFO_VERSION = "baseinfo_version";

        //create table
        public static final String CREATE = "create table if not exists " + TABLE_NAME + "(" +
                "id integer primary key autoincrement," +
                UID + " integer," +
                ACCOUNT + " text," +
                TEL + " text," +
                NICKNAME + " text," +
                REMARK + " text," +
                SEX + " integer," +
                BIRTHDAY + " text," +
                HEIGHT + " integer," +
                WEIGHT + " integer," +
                EMAIL + " text," +
                AVATAR_ID + " integer," +
                AVATAR_URL + " text," +
                TYPE + " text," +
                PERMISSION + " integer," +
                REMARK_VERSION + " integer," +
                BASEINFO_VERSION + " integer" +
                ");";
        // 删除表
        public static final String DELETE = "drop table " + TABLE_NAME;
        // 清空表
        public static final String CLEAN = "delete from " + TABLE_NAME;
    }


    //聊天表
    public class Chat {

        public static final String TABLE_NAME = "chat";

        public static final String ID = "id";
        public static final String FROM_ID = "from_id";
        public static final String TO_ID = "to_id";
        public static final String DATA = "data";
        public static final String CONTENT = "content";
        public static final String DATE = "date";
        public static final String TYPE = "type";
        public static final String STATE = "state";
        public static final String IS_READ = "is_read";

        //create table
        public static final String CREATE = "create table if not exists " + TABLE_NAME + "(" +
                ID + " integer primary key autoincrement," +
                FROM_ID + " integer," +
                TO_ID + " integer," +
                DATA + " text," +
                CONTENT + " text," +
                DATE + " integer," +
                TYPE + " integer," +
                STATE + " integer," +
                IS_READ + " integer" +
                ");";

        // 删除表
        public static final String DELETE = "drop table " + TABLE_NAME;
        // 清空表
        public static final String CLEAN = "delete from " + TABLE_NAME;
    }


}
