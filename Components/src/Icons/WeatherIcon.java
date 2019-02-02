package Icons;

import Glyphs.Glyph;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class WeatherIcon extends Icon {

    private ObjectProperty<Glyph.Weather> glyph = new SimpleObjectProperty<>(Glyph.Weather.CLOUD);

    public WeatherIcon()
    {
        this(Glyph.Weather.CLOUD);

    }

    public WeatherIcon(Glyph glyph) {
        size.set(DEFAULT_SIZE);
        setGlyphFont(glyph.getFont());
        setGlyph(glyph);

    }

    public ObjectProperty<Glyph.Weather> glyphProperty() {return glyph;}

    public Glyph.Weather getGlyph() {return glyph.get();}
    public void setGlyph(Glyph.Weather glyph) {
        this.glyph.set(glyph);
        setText(this.getGlyph().getGlyph());
    }


}
