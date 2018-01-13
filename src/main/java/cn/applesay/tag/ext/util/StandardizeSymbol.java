package cn.applesay.tag.ext.util;



public class StandardizeSymbol
{
  static boolean isUppercase = false;

  public static String standardize(String str) {
    String transformed = "";

    for (int i = 0; i < str.length(); i++) {
      char temp = str.charAt(i);
      transformed = transformed + getAlphabetNumber(temp);
    }
    return transformed;
  }

  public static char getAlphabetNumber(char ch) {
    if (!isUppercase) {
      if ((ch == 'A') || (ch == 65313) || (ch == 'a') || (ch == 65345))
        return 'a';
      if ((ch == 'B') || (ch == 65314) || (ch == 'b') || (ch == 65346))
        return 'b';
      if ((ch == 'C') || (ch == 65315) || (ch == 'c') || (ch == 65347))
        return 'c';
      if ((ch == 'D') || (ch == 65316) || (ch == 'd') || (ch == 65348))
        return 'd';
      if ((ch == 'E') || (ch == 65317) || (ch == 'e') || (ch == 65349))
        return 'e';
      if ((ch == 'F') || (ch == 65318) || (ch == 'f') || (ch == 65350))
        return 'f';
      if ((ch == 'G') || (ch == 65319) || (ch == 'g') || (ch == 65351))
        return 'g';
      if ((ch == 'H') || (ch == 65320) || (ch == 'h') || (ch == 65352))
        return 'h';
      if ((ch == 'I') || (ch == 65321) || (ch == 'i') || (ch == 65353))
        return 'i';
      if ((ch == 'J') || (ch == 65322) || (ch == 'j') || (ch == 65354))
        return 'j';
      if ((ch == 'K') || (ch == 65323) || (ch == 'k') || (ch == 65355))
        return 'k';
      if ((ch == 'L') || (ch == 65324) || (ch == 'l') || (ch == 65356))
        return 'l';
      if ((ch == 'M') || (ch == 65325) || (ch == 'm') || (ch == 65357))
        return 'm';
      if ((ch == 'N') || (ch == 65326) || (ch == 'n') || (ch == 65358))
        return 'n';
      if ((ch == 'O') || (ch == 65327) || (ch == 'o') || (ch == 65359))
        return 'o';
      if ((ch == 'P') || (ch == 65328) || (ch == 'p') || (ch == 65360))
        return 'p';
      if ((ch == 'Q') || (ch == 65329) || (ch == 'q') || (ch == 65361))
        return 'q';
      if ((ch == 'R') || (ch == 65330) || (ch == 'r') || (ch == 65362))
        return 'r';
      if ((ch == 'S') || (ch == 65331) || (ch == 's') || (ch == 65363))
        return 's';
      if ((ch == 'T') || (ch == 65332) || (ch == 't') || (ch == 65364))
        return 't';
      if ((ch == 'U') || (ch == 65333) || (ch == 'u') || (ch == 65365))
        return 'u';
      if ((ch == 'V') || (ch == 65334) || (ch == 'v') || (ch == 65366))
        return 'v';
      if ((ch == 'W') || (ch == 65335) || (ch == 'w') || (ch == 65367))
        return 'w';
      if ((ch == 'X') || (ch == 65336) || (ch == 'x') || (ch == 65368))
        return 'x';
      if ((ch == 'Y') || (ch == 65337) || (ch == 'y') || (ch == 65369))
        return 'y';
      if ((ch == 'Z') || (ch == 65338) || (ch == 'z') || (ch == 65370)) {
        return 'z';
      }
    }
    else {
      if ((ch == 'A') || (ch == 65313) || (ch == 'a') || (ch == 65345))
        return 'A';
      if ((ch == 'B') || (ch == 65314) || (ch == 'b') || (ch == 65346))
        return 'B';
      if ((ch == 'C') || (ch == 65315) || (ch == 'c') || (ch == 65347))
        return 'C';
      if ((ch == 'D') || (ch == 65316) || (ch == 'd') || (ch == 65348))
        return 'D';
      if ((ch == 'E') || (ch == 65317) || (ch == 'e') || (ch == 65349))
        return 'E';
      if ((ch == 'F') || (ch == 65318) || (ch == 'f') || (ch == 65350))
        return 'F';
      if ((ch == 'G') || (ch == 65319) || (ch == 'g') || (ch == 65351))
        return 'G';
      if ((ch == 'H') || (ch == 65320) || (ch == 'h') || (ch == 65352))
        return 'H';
      if ((ch == 'I') || (ch == 65321) || (ch == 'i') || (ch == 65353))
        return 'I';
      if ((ch == 'J') || (ch == 65322) || (ch == 'j') || (ch == 65354))
        return 'J';
      if ((ch == 'K') || (ch == 65323) || (ch == 'k') || (ch == 65355))
        return 'K';
      if ((ch == 'L') || (ch == 65324) || (ch == 'l') || (ch == 65356))
        return 'L';
      if ((ch == 'M') || (ch == 65325) || (ch == 'm') || (ch == 65357))
        return 'M';
      if ((ch == 'N') || (ch == 65326) || (ch == 'n') || (ch == 65358))
        return 'N';
      if ((ch == 'O') || (ch == 65327) || (ch == 'o') || (ch == 65359))
        return 'O';
      if ((ch == 'P') || (ch == 65328) || (ch == 'p') || (ch == 65360))
        return 'P';
      if ((ch == 'Q') || (ch == 65329) || (ch == 'q') || (ch == 65361))
        return 'Q';
      if ((ch == 'R') || (ch == 65330) || (ch == 'r') || (ch == 65362))
        return 'R';
      if ((ch == 'S') || (ch == 65331) || (ch == 's') || (ch == 65363))
        return 'S';
      if ((ch == 'T') || (ch == 65332) || (ch == 't') || (ch == 65364))
        return 'T';
      if ((ch == 'U') || (ch == 65333) || (ch == 'u') || (ch == 65365))
        return 'U';
      if ((ch == 'V') || (ch == 65334) || (ch == 'v') || (ch == 65366))
        return 'V';
      if ((ch == 'W') || (ch == 65335) || (ch == 'w') || (ch == 65367))
        return 'W';
      if ((ch == 'X') || (ch == 65336) || (ch == 'x') || (ch == 65368))
        return 'X';
      if ((ch == 'Y') || (ch == 65337) || (ch == 'y') || (ch == 65369))
        return 'Y';
      if ((ch == 'Z') || (ch == 65338) || (ch == 'z') || (ch == 65370))
        return 'Z';
    }
    if ((ch == '0') || (ch == 65296))
      return '0';
    if ((ch == '1') || (ch == 65297))
      return '1';
    if ((ch == '2') || (ch == 65298))
      return '2';
    if ((ch == '3') || (ch == 65299))
      return '3';
    if ((ch == '4') || (ch == 65300))
      return '4';
    if ((ch == '5') || (ch == 65301))
      return '5';
    if ((ch == '6') || (ch == 65302))
      return '6';
    if ((ch == '7') || (ch == 65303))
      return '7';
    if ((ch == '8') || (ch == 65304))
      return '8';
    if ((ch == '9') || (ch == 65305)) {
      return '9';
    }
    if ((ch == 65292) || (ch == ',') || (ch == 65104))
      return 65292;
    if ((ch == 65106) || (ch == '.') || (ch == 65294))
      return '.';
    if ((ch == '、') || (ch == 65105))
      return '、';
    if ((ch == '‘') || (ch == '`'))
      return '‘';
    if ((ch == '’') || (ch == '´'))
      return '’';
    if ((ch == '“') || (ch == '〝'))
      return '“';
    if ((ch == '”') || (ch == '〞'))
      return '”';
    if ((ch == '!') || (ch == 65111) || (ch == 65281))
      return 65281;
    if ((ch == 65311) || (ch == '?') || (ch == 65110))
      return 65311;
    if ((ch == 65306) || (ch == ':') || (ch == 65072) || (ch == 65109) || (ch == '∶'))
      return 65306;
    if ((ch == 65307) || (ch == ';') || (ch == 65108))
      return 65307;
    if ((ch == '-') || (ch == 65293) || (ch == '─') || (ch == 65123) || (ch == '–') ||
      (ch == '―') || (ch == '━'))
      return '-';
    if ((ch == 65288) || (ch == '(') || (ch == 65113) || (ch == '〔'))
      return 65288;
    if ((ch == 65289) || (ch == ')') || (ch == 65114) || (ch == '〕'))
      return 65289;
    if ((ch == '{') || (ch == 65371))
      return '{';
    if ((ch == '}') || (ch == 65373))
      return '}';
    if ((ch == '<') || (ch == '〈') || (ch == 65124) || (ch == 65308))
      return '<';
    if ((ch == '>') || (ch == '〉') || (ch == 65125) || (ch == 65310))
      return '>';
    if ((ch == '|') || (ch == 65372) || (ch == '│'))
      return '|';
    if ((ch == '/') || (ch == 65295) || (ch == '∕'))
      return '/';
    if ((ch == '@') || (ch == 65312))
      return '@';
    if ((ch == '&') || (ch == 65286))
      return '&';
    if ((ch == '#') || (ch == 65283))
      return '#';
    if ((ch == '%') || (ch == 65130) || (ch == 65285))
      return '%';
    if ((ch == '¥') || (ch == 65509))
      return '¥';
    if ((ch == '$') || (ch == 65284))
      return '$';
    if ((ch == '£') || (ch == '₤') || (ch == 65505))
      return '£';
    if ((ch == '€') || (ch == 59244))
      return '€';
    if ((ch == '+') || (ch == 65291))
      return '+';
    if ((ch == '=') || (ch == 65309))
      return '=';
    if ((ch == '×') || (ch == '╳'))
      return '×';
    if ((ch == '≥') || (ch == '≧'))
      return '≥';
    if ((ch == '≤') || (ch == '≦'))
      return '≤';
    if ((ch == 'Σ') || (ch == '∑'))
      return 'Σ';
    if ((ch == '【') || (ch == '〖'))
      return '【';
    if ((ch == '】') || (ch == '〗'))
      return '】';
    if ((ch == '「') || (ch == '『') || (ch == 65089) || (ch == 65091))
      return '「';
    if ((ch == '」') || (ch == '』') || (ch == 65090) || (ch == 65092)) {
      return '」';
    }
    if ((ch == '•') || (ch == '●') || (ch == '〇') || (ch == '■') || (ch == '◆') ||
      (ch == '◇') || (ch == '▲') || (ch == '△') || (ch == '★') ||
      (ch == '☆') || (ch == '®') || (ch == '卍') || (ch == '※')) {
      return '•';
    }

    return ch;
  }
}


/* Location:              /Users/rsong/Workspace/FudanDNN-NLPv4.0/FudanDNN-NLP4.0/lib/fudandnn/FudanDNN-NLP4.0.jar!/cn/edu/fudan/util/StandardizeSymbol.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */