import org.junit.jupiter.api.Test;

public abstract class Animal1 {
    {
        System.out.println("normal block");
    }
}

class Dog1 extends Animal1 {

}

class TestAnimal {
    @Test
    public void testAnimal() {
        Dog1 dog = new Dog1();
    }
}
