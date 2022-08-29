package com.pokercalc.cmd;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class Card
{
    private int rank;
    private String suit;
    private String strRank;

    //  constructor w/ int rank and str suit params -- gets String rank from hMapRanks
    public Card(int cardRank,String cardSuit)
    {
        this.rank = cardRank;
        this.suit = cardSuit;
        this.strRank = Poker.getStringRank(cardRank);     //hashmap getter func; pass int rank, returns corresponding string rank; e.g. pass '2' -> return "TWO"
    }

    //  constructor w/ all three params
    public Card(int cardRank,String cardSuit,String cardStrRank)
    {
        this.rank = cardRank;
        this.suit = cardSuit;
        this.strRank = cardStrRank;
    }

    // TODO:      <<  NOT NECESSARY ?    >>
    public Card(String cardStrRank, String cardSuit, HashMap<Integer,String> hashMap)
    {
        this.suit = cardSuit;
        this.strRank = cardStrRank;

        for (Map.Entry<Integer,String> entry:hashMap.entrySet())
        {
            if(Objects.equals(cardStrRank, entry.getValue()))
            {
                this.rank = (int) entry.getKey();
            }
        }
    }

    //  getters + setters for class vars
    public int getRank()                     {return rank;}
    private void setRank(int rank)            {this.rank = rank;}

    public String getSuit()                 {return suit;}
    private void setSuit(String suit)       {this.suit = suit;}

    public String getRankStr()               {return strRank;}
    private void setRankStr(String rankStr)   {this.strRank = rankStr;}
}
