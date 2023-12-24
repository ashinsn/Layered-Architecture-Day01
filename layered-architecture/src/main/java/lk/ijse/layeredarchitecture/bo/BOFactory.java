package lk.ijse.layeredarchitecture.bo;

import lk.ijse.layeredarchitecture.bo.custom.CustomerBoImpl;
import lk.ijse.layeredarchitecture.bo.custom.ItemBoImpl;
import lk.ijse.layeredarchitecture.bo.custom.PlaceOrderBoImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return(boFactory==null)?boFactory=new BOFactory():boFactory;
    }
    public static void getBOFactory(){}

    public enum BOTypes{
        CUSTOMER,ITEM,PLACE_ORDER
    }
    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER:
                return (SuperBO) new CustomerBoImpl();
            case ITEM:
                return (SuperBO) new ItemBoImpl();
            case PLACE_ORDER:
                return (SuperBO) new PlaceOrderBoImpl();
            default:
                return null;
        }
    }

}
