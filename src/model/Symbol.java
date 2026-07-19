package model;

public class Symbol {
    private final char value;

    public Symbol(char value){
        this.value = value;
    }

    public char getValue(){
        return value;
    }

    @Override
    public boolean equals(Object object){
        if(this == object){
            return true;
        }
        if(!(object instanceof Symbol)){
            return false;
        }

        return value == ((Symbol) object).value;
    }

    @Override
    public int hashCode(){
        return Character.hashCode(value);
    }

    @Override
    public String toString(){
        return String.valueOf(value);
    }
}
