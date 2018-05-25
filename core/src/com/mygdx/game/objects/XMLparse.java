package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.mygdx.game.MyGdxGame;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class XMLparse {

    public MyGdxGame game;
    private Map<String,Barrier> barriers = new HashMap<String,Barrier>();;

    private String strbarr;

    public XMLparse(MyGdxGame game){
        this.game = game;
    }

    public HashMap<String, String> XMLparseLangs(String lang) {
        HashMap<String, String> langs = new HashMap<String, String>();

        XmlReader.Element root = new XmlReader().parse(Gdx.files.internal("xml/langs.xml"));
        Array<XmlReader.Element> xml_langs = root.getChildrenByName("lang");

        for (XmlReader.Element el : xml_langs) {
            if (el.getAttribute("key").equals(lang)) {
                Array<XmlReader.Element> xml_strings = el.getChildrenByName("string");
                for (XmlReader.Element e : xml_strings) {
                    langs.put(e.getAttribute("key"), e.getText());
                }
            } else if (el.getAttribute("key").equals("en")) {
                Array<XmlReader.Element> xml_strings = el.getChildrenByName("string");
                for (XmlReader.Element e : xml_strings) {
                    langs.put(e.getAttribute("key"), e.getText());
                }
            }
        }

        return langs;
    }

    public TreeMap<String, Array<Array<String>>> getPos(String strLevel) {

        TreeMap<String, Array<Array<String>>> barrierPos = new TreeMap<String, Array<Array<String>>>();

        XmlReader.Element root = new XmlReader().parse(Gdx.files.internal("xml/levels/" + strLevel + ".xml")).getChildByName("positions");

        Array<XmlReader.Element> xml_pos = root.getChildrenByName("position");
        for (XmlReader.Element el : xml_pos) {
            Array<String> xy = new Array<String>();
            xy.add(el.getAttribute("x"));
            xy.add(el.getAttribute("y"));
            String barr =  el.getAttribute("barr");
            if (barrierPos.containsKey(barr))
                barrierPos.get(barr).add(xy);
            else {
                Array<Array<String>> adding = new Array<Array<String>>();
                adding.add(xy);
                barrierPos.put(barr, adding);
            }
        }

        return barrierPos;
    }

}
