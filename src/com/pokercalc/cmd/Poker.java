package com.pokercalc.cmd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Poker
{
    public enum ENUM_SUITS {SPADES, CLUBS, DIAMONDS, HEARTS}
    public enum ENUM_RANKS {TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE}


    private static final HashMap<Integer,String> hashMapRanks;
    private static final Card[] pokerDeck;
    private static int numRiverFlipped;

    static
    {
        hashMapRanks = initializeHashMapRanks();
        pokerDeck = initializeDeck();
        numRiverFlipped = 0;
    }

    private static Card[][] allPlayerHands;
    private static Card[] river;
    private static ArrayList<Card> cardsInput;        //make function to check if a card with the same suit and val has already been instantiated


//--------------------
//      initDeck()
//--------------------
    public static Card[] initializeDeck()
    {   //...............................
        Card[] cards = new Card[52];
        int constCtr = 0;

        try {
            for (ENUM_SUITS enumSuits : ENUM_SUITS.values()) {
                for(Map.Entry<Integer,String> mapValues: hashMapRanks.entrySet()){
                    Card newCard = new Card(mapValues.getKey(),enumSuits.toString(),mapValues.getValue());
                    cards[constCtr] = newCard;
                    constCtr++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cards;
    }


//OVERRIDDEN METHOD FOR MainApplication class
    public static Card[] initializeDeck(HashMap<Integer,String> hashmap)
    {
        Card[] cards = new Card[52];
        int constCtr = 0;

        try {
            for (ENUM_SUITS enumSuits : ENUM_SUITS.values()) {
                for(Map.Entry<Integer,String> mapValues : hashmap.entrySet()){
                    Card newCard = new Card(mapValues.getKey(),enumSuits.toString(),mapValues.getValue());
                    cards[constCtr] = newCard;
                    constCtr++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cards;
    }



/** ------------------------
 *  initializeHashMapRanks()
 *  ------------------------
 *
 **/

    public static HashMap<Integer,String> initializeHashMapRanks()
    {
//  initializes a hashmap which pairs int (hashmap Key) and String (hashmap value) versions of Card ranks, e.g. "TWO" == 2
        HashMap<Integer,String> map = new HashMap<>();      //  init local hashmap var
        int rankCtr = 2;                                    //  counter for int ranks, starting at lowest value 2

//  gets String value for each enum in ENUM_VALS.
        for (ENUM_RANKS enumRanks : ENUM_RANKS.values())
        {
            map.put(rankCtr,enumRanks.toString());                  //  String and corresponding int are added as map entries
            rankCtr++;
        }
        return map;
    }



/** ------------------------
 *      getters/setters
 *  ------------------------
 *
 **/

    public static String getStringRank(int cardRank)                {return hashMapRanks.get(cardRank);}    //  TODO: DELETE?
    public static HashMap<Integer,String> getHashMapRanks()         {return hashMapRanks;}

    public static Card[] getPokerDeck()                             {return pokerDeck;}
    //public static void setPokerDeck(Card[] pokerDeck)             {Poker.pokerDeck = pokerDeck;}

    public static int getNumRiverFlipped()                          {return numRiverFlipped;}
    public static void setNumRiverFlipped(int numRiverFlipped)      {Poker.numRiverFlipped = numRiverFlipped;}

    public static Card[] getRiver()                                 {return river;}
    public static void setRiver(Card[] river)                       {Poker.river = river;}

    public static ArrayList<Card> getCardsInput()                   {return cardsInput;}
    public static void setCardsInput(ArrayList<Card> cardsInput)    {Poker.cardsInput = cardsInput;}
}
