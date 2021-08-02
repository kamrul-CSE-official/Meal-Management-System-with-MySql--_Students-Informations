package mmstest;
class Informations{    
    private String member_id;
    private String name;
    private String phone_no;    
    public Informations(String member_id,String name,String phone_no){
        this.member_id = member_id;
        this.name = name;
        this.phone_no = phone_no;
    }    
    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }
    public void setName(String name) {
        this.name = name;
    }    
    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }    
    public String getMember_id() {
        return member_id;
    }
    public String getName() {
        return name;
    }    
    public String getPhone_no() {
        return phone_no;
    }    
}
