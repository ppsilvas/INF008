package br.edu.ifba.inf008.interfaces;

import java.util.ArrayList;

import br.edu.ifba.inf008.models.Book;
import br.edu.ifba.inf008.models.Loan;
import br.edu.ifba.inf008.models.User;

public interface IIOController
{
    public abstract <T> boolean saveData(ArrayList<Book>books,ArrayList<User>users,ArrayList<Loan>loans, int numberOfBooks, int numberOfUsers, int numberOfLoans);
    public abstract Object[] loadData();
}
