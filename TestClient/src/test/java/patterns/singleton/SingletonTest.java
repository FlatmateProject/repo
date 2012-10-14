package patterns.singleton;

import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

public class SingletonTest {

    @Test
    public void shouldCreateSingleInstance() {
        //given

        //when
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();

        //then
        assertThat(instance2).isNotNull();
        assertThat(instance).isNotNull().isEqualTo(instance2);
        assertThat(instance.getMessage()).isEqualTo(instance2.getMessage());
    }

    @Test
    public void shouldCreateNiceSingleInstance() {
        //given

        //when
        NiceSingleton instance = NiceSingleton.getInstance();
        NiceSingleton instance2 = NiceSingleton.getInstance();

        //then
        assertThat(instance2).isNotNull();
        assertThat(instance).isNotNull().isEqualTo(instance2);
        assertThat(instance.getMessage()).isEqualTo(instance2.getMessage());
    }
}
