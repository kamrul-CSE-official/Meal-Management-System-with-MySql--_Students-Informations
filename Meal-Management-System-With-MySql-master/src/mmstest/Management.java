package mmstest;

import java.util.ArrayList;

class Management{
    ArrayList<Informations> infoList = new ArrayList<>();
    ArrayList<Process> processList = new ArrayList<>();
    public void SaveInfoList(String member_id,String name,String phone_no){
        infoList.add(new Informations(member_id,name,phone_no));
    }   
    public void SaveProcessList(String member_id,String process_id,String meal,String expanditure,String due,String back){
        processList.add(new Process(member_id,process_id,meal,expanditure,due,back));
    }
    public void showInfo(){
        for(Informations info : infoList){
            System.out.println(info.getMember_id()+" "+info.getName()+info.getPhone_no());
        }
    }
    public void showProcess(){
        for(Process process : processList){
            System.out.println(process.getMem_id()+" "+process.getProcess_id()+" "+process.getMeal()+" "+process.getExpanditure());
        }        
    }    
}

