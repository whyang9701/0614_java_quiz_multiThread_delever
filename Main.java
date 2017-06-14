public class Main{
    public static void main(String args[]){
        final Object cargo = new Object();
        Person p1 = new Person("Worker 1");
        Person p2 = new Person("Worker 2");
        Person p3 = new Person("Worker 3");
        Person p4 = new Person("Worker 4");
        p1.lastPerson = p4;
        p1.nextPerson = p2;
        p2.lastPerson = p1;
        p2.nextPerson = p3;
        p3.lastPerson = p2;
        p3.nextPerson = p4;
        p4.lastPerson = p3;
        p4.nextPerson = p1;
        p1.cargo = cargo;
        // p2.cargo = cargo;
        // p3.cargo = cargo;
        // p4.cargo = cargo;
        p1.start();
        p2.start();
        p3.start();
        p4.start();
    }
}

class Person extends Thread{
    public Person(String name){
        super(name);
    }

    Person lastPerson = null;
    Person nextPerson = null;
    Object cargo = null;
    public void run(){
        while(true){
            this.cargo = lastPerson.pushCargo();
            System.out.println(super.getName()+"get cargo");
            
        }
        
        
    }

    public void getCargo(Object cargo){
        synchronized(cargo){
            this.cargo = cargo;
        }
    }
    public Object pushCargo(){
        while(this.cargo == null){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                
                }
            }
        synchronized(this.cargo){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            
            }
            System.out.println(super.getName() + "push cargo");
            Object o = this.cargo;
            cargo = null;
            return o;

        }
        
    }
}
