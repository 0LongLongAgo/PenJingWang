package sysshare.lq.com.penjingwang.event;



public class Event {
    public enum Type {
        ITEM, LIST
    }

    public Type type;
    public Object object;


    public Event(Type type, Object object) {
        this.type = type;
        this.object = object;
    }
}
