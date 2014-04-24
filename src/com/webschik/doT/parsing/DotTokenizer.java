package com.webschik.doT.parsing;

import com.intellij.psi.tree.IElementType;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class DotTokenizer implements Tokenizer {

    private String src;
    private List<DotToken> tokens;
    private HashMap<Character, IElementType> typesBySymbol = new HashMap<Character, IElementType>();

    public DotTokenizer() {
        this(null);
    }

    public DotTokenizer(String buffer) {
        init(buffer);
        parse();
    }

    private void init(String buffer) {
        src = buffer;
        tokens = new ArrayList<DotToken>();
        typesBySymbol.put('!', DotTokenTypes.OPEN_ESCAPED);
        typesBySymbol.put('=', DotTokenTypes.OPEN_UNESCAPED);
        typesBySymbol.put('?', DotTokenTypes.CONDITIONAL);
        typesBySymbol.put('~', DotTokenTypes.ITERATION);
        typesBySymbol.put('#', DotTokenTypes.OPEN_PARTIAL);
    }

    private void parse() {
        tokenize();
        validate();
    }

    private void tokenize() {
        int i,
            len,
            tokenStart,
            tokenEnd,
            openedDataPrefixesCount = 0;
        char current,
            next,
            prev,
            chr;

        IElementType tokenType;

        if (src != null) {
            len = src.length();

            for (i = 0; i < len; i++) {
                current = src.charAt(i);
                next = (i < len - 1) ? src.charAt(i + 1) : 0;
                prev = (i > 0) ? src.charAt(i - 1) : 0;
                tokenType = DotTokenTypes.CONTENT;
                tokenStart = i;
                tokenEnd = i + 1;

                if (current == '{' && next == current) {
                    tokenType = DotTokenTypes.OPEN;
                    tokenStart = i;
                    tokenEnd = i + 2;
                    i++;

                    if (openedDataPrefixesCount > 0) {
                        openedDataPrefixesCount--;
                    }

                    chr = (i == len - 1) ? 0 : src.charAt(i + 1);
                    if (typesBySymbol.containsKey(chr)) {
                        tokenType = typesBySymbol.get(chr);
                        tokenEnd++;
                        i++;

                        if (i < len - 1) {
                            // "defines" - {{##
                            if (chr == '#' && src.charAt(i + 1) == chr) {
                                tokenType = DotTokenTypes.OPEN_DEFINE;
                                tokenEnd++;
                                i++;
                            } else if (src.charAt(i + 1) == '?') {
                                tokenEnd++;
                            }
                        }
                    }
                } else if (current == '#' && next == '}' && (i < len - 1) && src.charAt(i + 1) == next) {
                    continue;
                } else if (current == '}' && next == current) {
                    if (openedDataPrefixesCount > 0) {
                        openedDataPrefixesCount--;
                    }
                    tokenStart = i;
                    tokenEnd = i + 2;
                    tokenType = DotTokenTypes.CLOSE;

                    i++;

                    // close defines
                    if (prev == '#') {
                        tokenType = DotTokenTypes.CLOSE_DEFINE;
                        tokenStart--;
                    }
                } else if (current == 'i' && next == 't' && i < len - 2 && src.charAt(i + 2) == '.') {
                    tokenType = DotTokenTypes.DATA_PREFIX;
                    tokenStart = i;
                    tokenEnd = i + 3;

                    i += 2;

                    if (openedDataPrefixesCount > 0) {
                        openedDataPrefixesCount--;
                    }

                    openedDataPrefixesCount++;
                } else {
                    if (openedDataPrefixesCount > 0) {
                        tokenType = DotTokenTypes.DATA;
                    }
                }

                tokens.add(new DotToken(tokenType, tokenStart, tokenEnd));
            }
        }
    }

    private void validate() {

    }

    public int getTokenStart(int index) {
        return tokens.get(index).getTokenStart();
    }

    public int getTokenEnd(int index) {
        return tokens.get(index).getTokenEnd();
    }

    public IElementType getTokenType(int index) {
        return (index < tokens.size()) ? tokens.get(index).getType() : null;
    }

    public void resetSequence(CharSequence buffer) {
        init(buffer.toString());
        parse();
    }

}
