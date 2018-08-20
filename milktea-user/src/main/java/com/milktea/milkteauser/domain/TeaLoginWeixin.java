package com.milktea.milkteauser.domain;

public class TeaLoginWeixin {
    private String weixinOpenid;

    private String weixinNickname;

    private String weixinSex;

    private String weixinProvince;

    private String city;

    private String country;

    private String headimgurl;

    private String privilege;

    private String unionid;
    
    private String accessToken;

    public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getWeixinOpenid() {
        return weixinOpenid;
    }

    public void setWeixinOpenid(String weixinOpenid) {
        this.weixinOpenid = weixinOpenid == null ? null : weixinOpenid.trim();
    }

    public String getWeixinNickname() {
        return weixinNickname;
    }

    public void setWeixinNickname(String weixinNickname) {
        this.weixinNickname = weixinNickname == null ? null : weixinNickname.trim();
    }

    public String getWeixinSex() {
        return weixinSex;
    }

    public void setWeixinSex(String weixinSex) {
        this.weixinSex = weixinSex == null ? null : weixinSex.trim();
    }

    public String getWeixinProvince() {
        return weixinProvince;
    }

    public void setWeixinProvince(String weixinProvince) {
        this.weixinProvince = weixinProvince == null ? null : weixinProvince.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl == null ? null : headimgurl.trim();
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege == null ? null : privilege.trim();
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
    }
}