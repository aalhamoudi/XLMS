package Icons;

import Glyphs.Glyph;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.text.Font;

public class MaterialDesignIcon extends Icon {

    private ObjectProperty<Glyph.MaterialDesign> glyph = new SimpleObjectProperty<>(Glyph.MaterialDesign.ACCOUNT);

    public MaterialDesignIcon()
    {
        this(Glyph.MaterialDesign.ACCOUNT);

    }

    public MaterialDesignIcon(Glyph.MaterialDesign glyph) {
        size.set(DEFAULT_SIZE);
        setGlyphFont(glyph.getFont());
        setGlyph(glyph);

    }


    public void setGlyphFont(Glyph.MaterialDesign glyph) {
        Font.loadFont(Glyph.Font.MaterialDesign.getFontStream(), size.get());
        setFont(new Font(Glyph.Font.MaterialDesign.getFamily(), size.get()));
    }

    public ObjectProperty<Glyph.MaterialDesign> glyphProperty() {return glyph;}
    public Glyph.MaterialDesign getGlyph() {return glyph.get();}
    public void setGlyph(Glyph.MaterialDesign glyph) {
        this.glyph.set(glyph);
        setText(this.getGlyph().getGlyph());
    }


}
