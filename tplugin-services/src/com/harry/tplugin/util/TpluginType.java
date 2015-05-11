package com.harry.tplugin.util;

public class TpluginType {
    /**
     * Enumeration of return codes
     */
     public enum TPLUGIN_RETURN_CODE
     {
         TPLUGIN_ERR_NONE                                         (0X00000000),
         TPLUGIN_ERR_FIELD                                                     (0X00000001),
         TPLUGIN_ERR_ARGUMENT                                                  (0X00000002),
         TPLUGIN_ERR_IMPLEMENT                                              (0X00000003),
         TPLUGIN_ERR_LOGIN_NAME                     (0X08000000),
         TPLUGIN_ERR_LOGIN_PASSWORD             (0X08000001),
         TPLUGIN_ERR_LOGIN_IP             (0X08000002),

         TPLUGIN_ERR_UNKNOWN                              (0X8000000E),
         
         TPLUGIN_RETURN_CODE_MAX                       (0xFFFFFFFF);
        
         private int value;
         TPLUGIN_RETURN_CODE(int value)
         {
             this.value = value;
         }
        
         public int getValue()
         {
             return value;
         }
         
         public static TPLUGIN_RETURN_CODE valueOf(int value)
         {
             for (int i = 0; i < TPLUGIN_RETURN_CODE.values().length; i ++)
             {
                 if (TPLUGIN_RETURN_CODE.values()[i].getValue() == value)
                     return TPLUGIN_RETURN_CODE.values()[i];
             }
             
             return TPLUGIN_ERR_UNKNOWN;
         }
     }
}
