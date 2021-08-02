package mmstest;

class Process{
    private String mem_id;    
    private String process_id;
    private String meal;
    private String expanditure;
    private String due;
    private String back;
    public Process(String mem_id,String process_id,String meal,String expanditure,String due,String back){
        this.mem_id = mem_id;
        this.process_id = process_id;
        this.meal = meal;
        this.expanditure = expanditure;
        this.due = due;
        this.back = back;
    }
    
    public void setProcess_id(String process_id) {
        this.process_id = process_id;
    }
    public void setMeal(String meal) {
        this.meal = meal;
    }
    public void setExpanditure(String expanditure) {
        this.expanditure = expanditure;
    }
    public String getProcess_id() {
        return process_id;
    }
    public String getMeal() {
        return meal;
    }
    public String getExpanditure() {
        return expanditure;
    }

    public void setMem_id(String mem_id) {
        this.mem_id = mem_id;
    }

    public void setDue(String due) {
        this.due = due;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public String getMem_id() {
        return mem_id;
    }

    public String getDue() {
        return due;
    }

    public String getBack() {
        return back;
    }
    
}