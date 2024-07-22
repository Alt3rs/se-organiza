package com.alt3rs.seorganiza.domain.activity.type;

public enum TypeTransaction {
    REVENUE("revenue"),
    EXPENSE("expense");

    private String value;

    TypeTransaction(final String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
