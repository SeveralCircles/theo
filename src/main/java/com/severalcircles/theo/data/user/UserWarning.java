package com.severalcircles.theo.data.user;

import java.util.Date;

public class UserWarning {
    Date time;
    TheoUser admin;
    TheoUser target;
    String msg;
    Date expires;
    String subject;
    String guildId;

    public UserWarning(Date time, TheoUser admin, TheoUser target, String msg, Date expires, String subject, String guildId){
        this.time = time;
        this.admin = admin;
        this.target = target;
        this.msg = msg;
        this.expires = expires;
        this.subject = subject;
        this.guildId = guildId;
    }

    public String getGuildId() {
        return guildId;
    }

    public void setGuildId(String guildId) {
        this.guildId = guildId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public UserWarning() {
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public TheoUser getAdmin() {
        return admin;
    }

    public void setAdmin(TheoUser admin) {
        this.admin = admin;
    }

    public TheoUser getTarget() {
        return target;
    }

    public void setTarget(TheoUser target) {
        this.target = target;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }
}
