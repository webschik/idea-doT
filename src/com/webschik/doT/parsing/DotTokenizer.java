package com.webschik.doT.parsing;

import com.intellij.psi.tree.IElementType;
import com.webschik.doT.config.DotConfig;

import java.util.*;

public class DotTokenizer implements Tokenizer {

    private String src;
    private List<DotToken> tokens;
    private HashMap<Character, IElementType> typesBySymbol = DotTokenTypesBySymbol.types;

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
    }

    private void parse() {
        tokenize();
        validate();
    }

    private void tokenize() {
        int i,
            index,
            len,
            tokenStart,
            tokenEnd,
            openedBracesCount = 0,
            varnamePatternLength,
            varnameLength,
            substringEnd,
            openedDataPrefixesCount = 0;
        char current,
            next,
            prev,
            chr;

        String varname = DotConfig.getVarName(),
               pattern = varname + ".";

        IElementType tokenType;

        varnameLength = varname.length();
        varnamePatternLength = pattern.length();

        if (src != null) {
            len = src.length();

            for (i = 0; i < len; i++) {
                current = src.charAt(i);
                next = (i < len - 1) ? src.charAt(i + 1) : 0;
                prev = (i > 0) ? src.charAt(i - 1) : 0;
                tokenType = DotTokenTypes.CONTENT;
                tokenStart = i;
                tokenEnd = i + 1;
                substringEnd = i + varnamePatternLength;

                if (substringEnd > len) {
                    substringEnd = len;
                }

                if (current == '{' && next == current) {
                    tokenType = DotTokenTypes.OPEN;
                    tokenStart = i;
                    tokenEnd = i + 2;
                    i++;

                    openedBracesCount++;

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
                } else if (current == '#' && next == '}') {
                    index = i + 2;

                    if ((index < len) && src.charAt(index) == next) {
                        continue;
                    }
                } else if (current == '}' && next == current) {
                    openedBracesCount--;

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
                } else if (src.substring(i, substringEnd).equals(pattern)) {
                    tokenType = DotTokenTypes.DATA_PREFIX;
                    tokenStart = i;

                    // varname and '.'
                    tokenEnd = i + varnamePatternLength;

                    i += varnameLength;

                    if (openedDataPrefixesCount > 0) {
                        openedDataPrefixesCount--;
                    }

                    openedDataPrefixesCount++;
                }

                // not found token
                if (tokenType == DotTokenTypes.CONTENT && openedBracesCount > 0) {
                    tokenType = DotTokenTypes.DATA;
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
