package com.sanbot.net;

import org.json.JSONObject;

/**
 * * Created by QHPC on 2016/4/19.
 * 设备参数消息
 * 用户向设备A获取数据
 * 用户向设备A设置数据
 */
public class SettingParams/* implements Parcelable*/ {

	private int fromId;//发起端ID

	private int toId;  //接收端ID

	private int type;//操作类型

	private int version = -1;//版本号

	private String params; //参数内容

	public SettingParams()
	{

	}

	public SettingParams(int fromId, int toId, int type, String data) {
		this.fromId = fromId;
		this.toId = toId;
		this.type = type;
		this.params = data;
	}

 /*   protected SettingParams(Parcel in) {
        fromId = in.readInt();
        toId = in.readInt();
        type = in.readInt();
        params = in.readString();
    }*/

   /* public static final Creator<SettingParams> CREATOR = new Creator<SettingParams>() {
        @Override
        public SettingParams createFromParcel(Parcel in) {
            return new SettingParams(in);
        }

        @Override
        public SettingParams[] newArray(int size) {
            return new SettingParams[size];
        }
    };*/

    public int getFromId() {
        return fromId;
    }

	public void setFromId(int fromId) {
		this.fromId = fromId;
	}

	public int getToId() {
		return toId;
	}

	public void setToId(int toId) {
		this.toId = toId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getError() {
		int result = 1;
		try {
			if (params == null) {
				return result;
			}

			JSONObject obj = new JSONObject(params);
			result = obj.optInt("result", 1);
			if (result !=1) {
				int error_code = obj.optInt("error_code", 0);
				if (error_code != 0) {
					result = error_code;
				}
			}
        } catch (Exception ignored) {

		}

		return result;
	}

	public String toString() {
        String buffer = "[" +
                "fromId:" +
                fromId +
                " toId:" +
                toId +
                " type:" +
                type +
                " params:" +
                params +
                "]";

        return buffer;
    }

  /*  @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(fromId);
        dest.writeInt(toId);
        dest.writeInt(type);
        dest.writeString(params);
    }*/
}
