package Icons;

import Glyphs.Glyph;
import javafx.beans.NamedArg;
import javafx.beans.property.ObjectProperty;

public class UniIcon extends Icon {

    //private ObjectProperty<Glyph> glyph = new SimpleObjectProperty<>();
    public UniIcon()
    {
        this(Glyph.Weather.CLOUD);

    }
    public UniIcon(@NamedArg("glyph") String glyph) {
        size.set(DEFAULT_SIZE);
        setGlyph(glyph);
        setGlyphFont(super.glyph.get().getFont());
    }

    public UniIcon(Glyph glyph) {
        size.set(DEFAULT_SIZE);
        setGlyphFont(glyph.getFont());
        setGlyph(glyph);
        

    }

    public ObjectProperty<Glyph> glyphProperty() {return glyph;}
    public String getGlyph() {return glyph.get().getName();}
    public void setGlyph(String glyph) {
        super.setGlyph(glyph);
        setText(super.glyph.get().getGlyph());
    }

    public void setGlyphFont(Glyph.Font font) {
        super.setGlyphFont(font);
        setGlyph(font.getDefaultGlyph());
    }
    public ObjectProperty<Glyph.Font> glyphFontProperty() {return glyphFont;}
    public Glyph.Font getGlyphFont() {return glyphFont.get();}

}
