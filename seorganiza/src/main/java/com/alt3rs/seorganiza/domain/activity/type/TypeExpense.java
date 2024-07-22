package com.alt3rs.seorganiza.domain.activity.type;

public enum TypeExpense {
    BILL("bill"),
    EDUCATION("education"),
    ENTERTAINMENT("entertainment"),
    FOOD("food"),
    OTHERS("others"),
    NULL("");

    private String value;

    TypeExpense(final String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
