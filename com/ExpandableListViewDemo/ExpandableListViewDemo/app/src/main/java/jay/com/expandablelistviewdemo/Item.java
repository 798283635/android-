package jay.com.expandablelistviewdemo;

/**
 * Created by Jay on 2015/9/25 0025.
 */
public class Item {

    private int iId;
    private String iName;
    private String iMsg;
    public Item() {
    }




    public Item(int iId, String iName,String iMsg) {
        this.iId = iId;
        this.iName = iName;
        this.iMsg = iMsg;
    }

    public int getiId() {
        return iId;
    }

    public String getiName() {
        return iName;
    }

    public void setiId(int iId) {
        this.iId = iId;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }

    public String getiMsg() {
        return iMsg;
    }

    public void setiMsg(String iMsg) {
        this.iMsg = iMsg;
    }
}
