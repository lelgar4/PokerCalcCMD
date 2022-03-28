package com.pokercalc.cmd;

import java.util.HashMap;
import java.util.Map;

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

    //      <<  NOT NECESSARY ?    >>
    public Card(String cardStrRank, String cardSuit, HashMap<Integer,String> hashMap)
    {
        this.suit = cardSuit;
        this.strRank = cardStrRank;
        for (Map.Entry entry:hashMap.entrySet())
        {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println(entry);
            if(cardStrRank == entry.getValue()){
                System.out.println("constructor...");
                System.out.println(cardStrRank + "//" + entry.getValue() + "//" + entry.getKey());
                this.rank = (int) entry.getKey();
            }
        }
        System.out.println("end constructor");
        //this.rank = hMapRanks.keySet()             //  how to get hashMap KEY (int rank) that corresponds to a known VALUE (str rank)
    }

    //  getters + setters for class vars
    public int getRank()                     {return rank;}
    private void setRank(int rank)            {this.rank = rank;}

    public String getSuit()                 {return suit;}
    private void setSuit(String suit)       {this.suit = suit;}

    public String getRankStr()               {return strRank;}
    private void setRankStr(String rankStr)   {this.strRank = rankStr;}
}
