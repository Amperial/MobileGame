package ninja.amp.mobilegame.objects.characters.movement;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public abstract class Body {

    private Set<BodyPart> parts = new TreeSet<BodyPart>(new Comparator<BodyPart>() {
        @Override
        public int compare(BodyPart o1, BodyPart o2) {
            if (o1.getDepth() < o2.getDepth()) {
                return -1;
            } else if (o1.getDepth() > o2.getDepth()) {
                return 1;
            } else {
                return 0;
            }
        }
    });
    private Set<BodyPart> flippedParts = new TreeSet<BodyPart>(new Comparator<BodyPart>() {
        @Override
        public int compare(BodyPart o1, BodyPart o2) {
            if (o1.getFlippedDepth() < o2.getFlippedDepth()) {
                return -1;
            } else if (o1.getFlippedDepth() > o2.getFlippedDepth()) {
                return 1;
            } else {
                return 0;
            }
        }
    });

    public void addBodyPart(BodyPart part) {
        parts.add(part);
        flippedParts.add(part);
    }

    public abstract Vector2 position();

    public void draw(Batch batch, float delta, boolean flipped) {
        if (flipped) {
            for (BodyPart part : flippedParts) {
                part.draw(batch, delta, true);
            }
        } else {
            for (BodyPart part : parts) {
                part.draw(batch, delta, false);
            }
        }
    }

    public void setScale(float scale) {
        for (BodyPart part : parts) {
            part.setScale(scale);
        }
    }

}
