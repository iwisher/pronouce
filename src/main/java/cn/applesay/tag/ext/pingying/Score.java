package cn.applesay.tag.ext.pingying;

public class Score {
  private int length = 0;
  private int predLength = 0;
  private String[] pinyin = null;
  private String[] standard = null;
  private int[] standardIndex = null;
  private float[] syllableScore = null;
  private float[] consonantScore = null;
  private float[] vowelScore = null;
  private float totalSyllableScore = 0.0F;
  private float totalConsonantScore = 0.0F;
  private float totalVowelScore = 0.0F;
  private float fluencyScore = 0.0F;




  public Score(int length, int predLength, String[] pinyin, String[] standard, int[] standardIndex, float[] syllableScore, float[] consonantScore, float[] vowelScore, float totalSyllableScore, float totalConsonantScore, float totalVowelScore, float fluencyScore)
  {
    this.length = length;
    this.predLength = predLength;
    this.pinyin = pinyin;
    this.standard = standard;
    this.standardIndex = standardIndex;
    this.syllableScore = syllableScore;
    this.consonantScore = consonantScore;
    this.vowelScore = vowelScore;
    this.totalSyllableScore = totalSyllableScore;
    this.totalConsonantScore = totalConsonantScore;
    this.totalVowelScore = totalVowelScore;
    this.fluencyScore = fluencyScore;
  }

  public String toString() {
    String str = "Prediction: ";
    for (int i = 0; i < this.predLength; i++) {
      str = str + this.pinyin[i] + " ";
    }
    str = str + "\r\n";
    str = str + "Standard: ";
    for (int i = 0; i < this.predLength; i++) {
      str = str + this.standard[i] + "/" + this.standardIndex[i] + " ";
    }
    str = str + "\r\n";
    str = str + "Length: " + this.length + "\r\n";
    str = str + "Syllable Score (whole): " + this.totalSyllableScore + "\r\n";
    str = str + "Consonant Score (whole): " + this.totalConsonantScore + "\r\n";
    str = str + "Vowel Score (whole): " + this.totalVowelScore + "\r\n";
    str = str + "Fluency Score (whole): " + this.fluencyScore + "\r\n";

    str = str + "Syllable Score (detail):\r\n  ";
    for (int i = 0; i < this.length; i++) {
      str = str + this.syllableScore[i] + "\t";
    }
    str = str + "\r\n";

    str = str + "Consonant Score (detail):\r\n  ";
    for (int i = 0; i < this.length; i++) {
      str = str + this.consonantScore[i] + "\t";
    }
    str = str + "\r\n";

    str = str + "Vowel Score (detail):\r\n  ";
    for (int i = 0; i < this.length; i++) {
      str = str + this.vowelScore[i] + "\t";
    }
    str = str + "\r\n";

    return str;
  }

  public int getLength() {
    return this.length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public int getPredLength() {
    return this.predLength;
  }

  public void setPredLength(int predLength) {
    this.predLength = predLength;
  }

  public String[] getPinyin() {
    return this.pinyin;
  }

  public void setPinyin(String[] pinyin) {
    this.pinyin = pinyin;
  }

  public String[] getStandard() {
    return this.standard;
  }

  public void setStandard(String[] standard) {
    this.standard = standard;
  }

  public int[] getStandardIndex()
  {
    return this.standardIndex;
  }

  public void setStandardIndex(int[] standardIndex) {
    this.standardIndex = standardIndex;
  }

  public float[] getSyllableScore() {
    return this.syllableScore;
  }

  public void setSyllableScore(float[] syllableScore) {
    this.syllableScore = syllableScore;
  }

  public float[] getConsonantScore() {
    return this.consonantScore;
  }

  public void setConsonantScore(float[] consonantScore) {
    this.consonantScore = consonantScore;
  }

  public float[] getVowelScore() {
    return this.vowelScore;
  }

  public void setVowelScore(float[] vowelScore) {
    this.vowelScore = vowelScore;
  }

  public float getTotalSyllableScore() {
    return this.totalSyllableScore;
  }

  public void setTotalSyllableScore(float totalSyllableScore) {
    this.totalSyllableScore = totalSyllableScore;
  }

  public float getTotalConsonantScore() {
    return this.totalConsonantScore;
  }

  public void setTotalConsonantScore(float totalConsonantScore) {
    this.totalConsonantScore = totalConsonantScore;
  }

  public float getTotalVowelScore() {
    return this.totalVowelScore;
  }

  public void setTotalVowelScore(float totalVowelScore) {
    this.totalVowelScore = totalVowelScore;
  }

  public float getFluencyScore() {
    return this.fluencyScore;
  }

  public void setFluencyScore(float fluencyScore) {
    this.fluencyScore = fluencyScore;
  }
}
