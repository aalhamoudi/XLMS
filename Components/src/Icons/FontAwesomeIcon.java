package Icons;

import Glyphs.Glyph;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.text.Font;

public class FontAwesomeIcon extends Icon {

    private ObjectProperty<Glyph.FontAwesome> glyph = new SimpleObjectProperty<>(Glyph.FontAwesome.ANCHOR);

    public FontAwesomeIcon()
    {
        this(Glyph.FontAwesome.ANCHOR);

    }

    public FontAwesomeIcon(Glyph.FontAwesome glyph) {
        size.set(DEFAULT_SIZE);
        setGlyphFont(glyph.getFont());
        setGlyph(glyph);

    }


    public void setGlyphFont(Glyph.FontAwesome glyph) {
        Font.loadFont(Glyph.Font.FontAwesome.getFontStream(), size.get());
        setFont(new Font(Glyph.Font.FontAwesome.getFamily(), size.get()));
    }

    public ObjectProperty<Glyph.FontAwesome> glyphProperty() {return glyph;}
    public Glyph.FontAwesome getGlyph() {return glyph.get();}
    public void setGlyph(Glyph.FontAwesome glyph) {
        this.glyph.set(glyph);
        setText(this.getGlyph().getGlyph());
    }


}
