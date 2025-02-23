package br.edu.ifba.inf008.interfaces;

import java.util.List;

import br.edu.ifba.inf008.models.Library;

public interface IIOController
{
    public abstract <T> boolean saveData(Library data, int numberOfBooks, int numberOfUsers, int numberOfLoans);
    public abstract Object[] loadData();
}
