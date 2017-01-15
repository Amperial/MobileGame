package ninja.amp.engine.objects.entities.effects;

public interface Effect {

    boolean active();

    void update(float delta);

}
