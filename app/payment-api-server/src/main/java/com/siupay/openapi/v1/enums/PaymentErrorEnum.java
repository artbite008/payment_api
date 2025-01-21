package com.siupay.openapi.v1.enums;//package com.siupay.openapi.v1.enums;
//
//import java.util.stream.Stream;
//
//import lombok.Getter;
//
//@Getter
//public enum PaymentErrorEnum {
//    SUCCESS("1000","success"),
//    UNKNOW("9999","unknow"),
//
//    //1xxx: 代表业平台错误
//    //内部业务错误10XX
//    ORDER_EXIST("1001","order does not exist",DepositErrorCode.ORDER_NOT_EXIST),
//    AMOUNT_UN_ENOUGH("1002","insufficient balance",DepositErrorCode.CUSTOMER_NO_BALANCE),
//    PARAM_MISS("1003","missing parameters",DepositErrorCode.VALIDATE_PARAMS_IS_NULL),
//    PARAM_ERROR("1004","parameter error",DepositErrorCode.INPUT_PARAMETER_ERROR),
//    VALIDATE_ERROR("1005","validate error",DepositErrorCode.VALIDATOR_ERROR),
//    RECORD_ALREADY_EXISTS("1006","record already exists"),
//    UPLOAD_FILE_ERROR("1007","upload file error"),
//    STATUS_ERROR("1008","status error",DepositErrorCode.ORDER_STATUS_ERROR),
//    RECEIVE_FORBIDDEN("1009","receive forbidden"),
//    REFUND_FORBIDDEN("1010","refund forbidden"),
//    WITHDRAW_FORBIDDEN("1011","withdraw forbidden"),
//    PAY_FORBIDDEN("1012","pay forbidden"),
//    REFUND_ALREADY_EXISTS("1013","refund already exists"),
//    REFUND_ALREADY_DONE("1014","all refunds have been completed, no refunds can be initiated",DepositErrorCode.FAIT_AMOUNT_OVER_LIMIT),
//    REFUND_UNAUTHORIZED("1015","the kyc is not completed, no refund can be initiated"),
//    RECORD_NOT_EXIST("1016","record not exist",DepositErrorCode.ORDER_NOT_EXIST),
//    FILE_NOT_EXISTS("1017","file not exists"),
//    BUSINESS_ERROR("1018","business error"),
//    DOWNLOAD_FILE_ERROR("1019","download file error"),
//    RPC_ERROR("1020", "rpc error"),
//    ORDER_OVER("1021", "the order has ended"),
//    ORDER_IN_PROCESSING("1022", "the order is already being processed",DepositErrorCode.REPEAT_TRADE),
//    ORDER_IN_PENDING("1023", "the order status is being pending",DepositErrorCode.REPEAT_TRADE),
//    ORDER_UPDATE_FAILED("1024", "the order update failed",DepositErrorCode.UPDATE_ORDER_ERROR),
//    ORDER_FAILED("1025", "the order channel failed"),
//    ORDER_AMOUNT_DIFF("1026", "the order amount with channel callback not the same"),
//
//    //内部技术错误19XX
//    //19XX全部使用 system unavailable
//    //---------------
//    CONNECTION_FAILED("1901","system connection failed",DepositErrorCode.SYSTEM_UNAVAILABLE),
//    FORMAT_FAILED("1902","system internal format error",DepositErrorCode.SYSTEM_UNAVAILABLE),
//    FREQUENTLY_REQUEST("1904","requests are too frequent",DepositErrorCode.SYSTEM_UNAVAILABLE),
//    DB_EXCEPTION("1997","db exception",DepositErrorCode.SYSTEM_UNAVAILABLE),
//    SYSTEM_CONFIG_ERROR("1998","system configuration error",DepositErrorCode.SYSTEM_UNAVAILABLE),
//    SERVER_ERROR("1999","server error",DepositErrorCode.SYSTEM_UNAVAILABLE),
//
//
//
//    //2xxx：代表业务方（Shopline）错误
//    APPID_INVALID("2001","appid does not exist"),
//    SIGN_INVALID("2002","sign invalid"),
//    USER_INFO_INVALID("2004","user info invalid"),
//    PAYOUT_ACCOUT_UPPER_LIMIT("2005","The number of accounts has reached the limit."),
//    PAYOUT_ACCOUT_REPEAT("2006","The account already exists."),
//    PAYOUT_WITHDRAWAL_MIN_LIMIT("2007", "The minimum withdrawal amount is %s."),
//    PAYOUT_WITHDRAWAL_MAX_LIMIT("2008", "The maximum withdrawal amount is %s."),
//    PAYOUT_WITHDRAWAL_FEE_ERROR("2009", "An error occurred."),
//    PAYOUT_WITHDRAWAL_FEE_OVER("2010", "fee over withdrawal amount"),
//    WALLET_PAYIN_MIN_LIMIT("2011", "The minimum withdrawal amount is %s."),
//    WALLET_PAYIN_MAX_LIMIT("2012", "The maximum withdrawal amount is %s."),
//    WALLET_AMOUNT_DIFF("2013", "The wallet order amount does not the same as channel."),
//    ORDER_INIT_FAILED("2014", "The order init failed."),
//    ORDER_NOT_PENDING("2015", "The order not pending."),
//
//    //3xxx：代表风控错误
//    RISK_CONNECTION_FAILED("3001","risk control connection failed"),
//    CALL_TOO_FREQUENCY("3002","call too frequency"),
//    USER_KYC_LEVEL("3003","Identity verification not satisified.",DepositErrorCode.KYC_LEVEL_NOT_SUPPORT),
//
//
//    //4xxx: 渠道方错误
//    //4xxx 全部抛出 channel error
//    CHANNEL_CONNECTION_FAILED("4001","channel connection failed",DepositErrorCode.CHANNEL_ERROR),
//    CHANNEL_ERROR("4002","channel error",DepositErrorCode.CHANNEL_ERROR),
//    CHANNEL_RESPONSE_TIMEOUT("4003","channel response timed out",DepositErrorCode.CHANNEL_ERROR),
//    CHANNEL_REJECTED("4004","channel rejected",DepositErrorCode.CHANNEL_ERROR),
//    CHANNEL_REVERSED("4005","channel reversed",DepositErrorCode.CHANNEL_ERROR),
//    CHANNEL_NO_DATA("4006","channel no data",DepositErrorCode.CHANNEL_ERROR),
//    CHANNEL_RETURN_ERROR("4007","channel return error",DepositErrorCode.CHANNEL_ERROR),
//    CHANNEL_FAILED("4008","channel failed",DepositErrorCode.CHANNEL_ERROR),
//    CHANNEL_SIGN_ERROR("4009", "channel sign error",DepositErrorCode.CHANNEL_ERROR);
//
//
//
//
//
//
//    private String code;
//    private String msg;
//    private DepositErrorCode depositError;
//
//    public static PaymentErrorEnum getErrorCodeByCode(String code){
//        if(code != null){
//            return Stream.of(PaymentErrorEnum.values())
//                    .filter(e->e.getCode().equalsIgnoreCase(code))
//                    .findFirst()
//                    .orElse(UNKNOW);
//        }
//        return UNKNOW;
//    }
//
//    PaymentErrorEnum(String code, String msg) {
//        this.code = code;
//        this.msg = msg;
//    }
//    PaymentErrorEnum(String code, String msg,DepositErrorCode depositError) {
//        this.code = code;
//        this.msg = msg;
//        this.depositError = depositError;
//    }
//}
