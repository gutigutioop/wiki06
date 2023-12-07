package app;

import java.util.List;
import java.util.Scanner;

import constants.Const;
import dto.WikiDto;
import model.WikiReader;

/**
 * ファイルベースWikiアプリ
 * 
 * Wikiアプリ開発06 - 選んだ番号のwikiを表示しよう！
 *
 * Mainクラス
 * ファイルベースWikiアプリの実行エントリポイントを持つクラス
 * @author　Say Consulting Group
 * @version　1.0.0
 */
public class Main {

  /**
   * mainメソッド
   * ファイルベースwikiアプリのエントリポイント
   * @param args コマンドライン引数(未使用)
   */
  public static void main(String[] args) {

    // ウェルカムメッセージ表示
    System.out.println(Const.WELCOME_MESSAGE);

    // wikiのリスト読み込み
    List<WikiDto> list = WikiReader.getAll();

    // 各値初期化
    int inpVal = 0;
    int listSize = list.size();
    boolean next = true;

    // 標準入力のスキャナーを生成
    try (Scanner sc = new Scanner(System.in)) {   //閉じる必要あるときに自動でとじれる。finallyにかかなくてもよい。
      // キーボードから0が入力されるまでループする
      do {
        // wikiのリスト表示
        showIndex(list);
        System.out.print(Const.IMPUT_MESSAGE);
        // キーボードからの入力を受け取り変数に格納
        String s = sc.nextLine();

        try {
          inpVal = Integer.parseInt(s);

          if (inpVal > listSize || inpVal < 0) {
            System.out.println(String.format("0～%dまでの数値を入力してください。", listSize));
          } else if (inpVal == 0) {
            // 終了する
            next = false;
          } else {
            // 指定番号のwikiを表示
            WikiDto d = list.get(inpVal - 1);  //list 配列のラッパークラスなので配列0番から始まる。筆者が書いたwiki(inpVal)は１番から始まる。なので１を引く。
            System.out.println(d);
          }
        } catch (NumberFormatException e) {
          System.out.println("数値を入力してください。");
        }

      } while(next);
    }

    // 終了メッセージを出力
    System.out.println(Const.FAIRWELL_MESSAGE);
  }

  /**
   * showIndexメソッド
   * wikiのリストを表示する
   * 繰り返し実行される一覧表示処理を外出しし、mainメソッドのコードを読みやすくする
   * 一行に表示するwikiの数はConstクラスの定数で変更可能
   * @param list wikiDtoのリスト
   */
  private static void showIndex(List<WikiDto> list) {
    int i = 0;
    for (WikiDto item : list) {
      System.out.print(item.index());

      // 一定数の表示後は改行し、改行しない場合は余白を表示
      String margin = i % Const.INDEX_LENGTH == Const.INDEX_LENGTH - 1 ? "\n\n" : "  ";
      System.out.print(margin);
      i++;
    }
  }
}
