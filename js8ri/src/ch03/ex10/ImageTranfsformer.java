package ch03.ex10;

import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class ImageTranfsformer {

	public static void main(String[] args) {
        UnaryOperator<Color> op = Color::brighter;
        Image image = null;
        /*
         * UnaryOperator<Color>がtransformの引数として定義されているが、
         * compase()の戻り値の型はFunction<Color,Color>なので型が不整合となるためエラー
         *
         * ストラクチャルであればColorを受け取りColorを返すという構造であれば同じものと解釈されるため
         * Functional<Color, Color>とUnaryOperator<Color>では構造が等しいと言えるので適用できる
         * ノミナル型付けの場合今回の場合のように構造は同じでも宣言が異なるため同一とは解釈されない
         *
         */
//        Image finalImage = transform(image, op.compose(Color::grayscale));

    }

	 public static Image transform(Image in, UnaryOperator<Color> transformer) {

		 return new WritableImage((int)in.getWidth(), (int)in.getHeight());
	 }
}
