import interfaces.IBinaryTree;

import java.util.ArrayList;

public class Multilevel {
    private IBinaryTree<Person> root;
    private final ArrayList<Person> listPersonsByCountry;
    private final ArrayList<IBinaryTree<Person>> listBinaryTreeByName;
    private int numPersonsWithoutReferrers;
    private final int MONEY_PEER_REFERRERS;
    
    Multilevel()
    {
        this.root = null;
        this.listPersonsByCountry = new ArrayList<>();
        this.listBinaryTreeByName = new ArrayList<>();
        this.MONEY_PEER_REFERRERS = 100;
    }
    
    private void setListPersonsByCountry(IBinaryTree<Person> r, String country)
    {
        if (r == null)
            return;

        if (r.getData().getCountry().equals(country)) {
            this.listPersonsByCountry.add(r.getData());
        }
        
        setListPersonsByCountry(r.left(), country);
        setListPersonsByCountry(r.right(), country);
    }

    private void setListBinaryTreeByName(IBinaryTree<Person> r, String name)
    {
        if (r == null)
            return;
        
        if (r.getData().getName().equals(name)) {
            this.listBinaryTreeByName.add(r);
        }
        setListBinaryTreeByName(r.left(), name);
        setListBinaryTreeByName(r.right(), name);
    }
    
    private int sumAgePersons(IBinaryTree<Person> r)
    {
        if (r == null)
            return 0;
        
        return r.getData().getAge() + sumAgePersons(r.right()) + sumAgePersons(r.left());
    }
    
    private int weight(IBinaryTree<Person> r)
    {
        if (r == null)
            return 0;
        
        return 1 + weight(r.left()) + weight(r.right());
    }

    private void countLeaves(IBinaryTree<Person> r)
    {
        if (r == null)
            return;

        if (r.left() == null && r.right() == null)
            this.numPersonsWithoutReferrers++;

        countLeaves(r.left());
        countLeaves(r.right());
    }

    private boolean insertReferrer(IBinaryTree<Person> r, Person person, Person referrer)
    {
        if (this.root == null) {
            this.root = new BinaryTree<>(referrer);
            return true;
        }

        if (r == null)
            return false;

        if (r.getData().equals(person)) {
            if (r.left() == null) {
                r.linkLeft(new BinaryTree<>(referrer));
                return true;
            }
            else if (r.right() == null) {
                r.linkRight(new BinaryTree<>(referrer));
                return true;
            }

            return false;
        }

        return insertReferrer(r.left(), person, referrer) || insertReferrer(r.right(), person, referrer);
    }

    public boolean insertReferrer(Person person, Person referrer)
    {
        return this.insertReferrer(this.root, person, referrer);
    }
    
    public ArrayList<Person> listPersonsByCountry(String country)
    {
        this.listPersonsByCountry.clear();
        setListPersonsByCountry(this.root, country);
        return this.listPersonsByCountry;
    }
    
    public float meanAge()
    {
        int numPersons = this.weight(this.root);

        if (numPersons == 0)
            return numPersons;

        int sumAges = this.sumAgePersons(this.root);

        return (float) sumAges / numPersons;
    }
    
    public ArrayList<Person> searchByName(String name)
    {
        this.listBinaryTreeByName.clear();
        this.setListBinaryTreeByName(this.root, name);

        if (this.listBinaryTreeByName.isEmpty())
            return null;

        ArrayList<Person> listPersonsByName = new ArrayList<>();

        for (IBinaryTree<Person> binaryTree: this.listBinaryTreeByName) {
            listPersonsByName.add(binaryTree.getData());
        }

        return listPersonsByName;
    }

    public ArrayList<Integer> calculateSalaryByName(String name)
    {
        ArrayList<Integer> salaries = new ArrayList<>();
        
        this.setListBinaryTreeByName(this.root, name);
        
        for (IBinaryTree<Person> binaryTree : this.listBinaryTreeByName) {
            salaries.add(this.MONEY_PEER_REFERRERS * (this.weight(binaryTree) - 1));
        }
        
        return salaries;
    }

    public int getNumPersonsWithoutReferrers() {
        this.numPersonsWithoutReferrers = 0;
        this.countLeaves(this.root);

        return this.numPersonsWithoutReferrers;
    }
}
