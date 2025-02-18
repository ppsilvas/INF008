package br.edu.ifba.inf008.interfaces;

import java.util.List;

import br.edu.ifba.inf008.models.Library;

public interface IIOController
{
    public static <T> boolean saveData(List<T> data, String fileName, int numberOfBooks, int numberOfUsers, int numberOfLoans){
        return false;
    }
    public static Object[] loadData(String fileName){
        return new Object[]{
            new Library(),
            0,
            0,
            0
        };    }
}
