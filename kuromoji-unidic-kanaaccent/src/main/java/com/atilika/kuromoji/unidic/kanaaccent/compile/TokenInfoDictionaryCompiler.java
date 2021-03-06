/**
 * Copyright 2010-2015 Atilika Inc. and contributors (see CONTRIBUTORS.md)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.  A copy of the
 * License is distributed with this work in the LICENSE.md file.  You may
 * also obtain a copy of the License from
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.atilika.kuromoji.unidic.kanaaccent.compile;

import com.atilika.kuromoji.compile.AbstractTokenInfoDictionaryCompiler;
import com.atilika.kuromoji.dict.GenericDictionaryEntry;
import com.atilika.kuromoji.unidic.kanaaccent.dict.DictionaryEntry;
import com.atilika.kuromoji.util.DictionaryEntryLineParser;

import java.util.ArrayList;
import java.util.List;

public class TokenInfoDictionaryCompiler extends AbstractTokenInfoDictionaryCompiler<DictionaryEntry> {

    public TokenInfoDictionaryCompiler(String encoding) {
        super(encoding);
    }

    @Override
    protected DictionaryEntry parse(String line) {
        String[] fields = DictionaryEntryLineParser.parseLine(line);
        return new DictionaryEntry(fields);
    }

    @Override
    protected GenericDictionaryEntry generateGenericDictionaryEntry(DictionaryEntry entry) {
        List<String> pos = extractPosFeatures(entry);
        List<String> features = extractOtherFeatures(entry);

        return new GenericDictionaryEntry.Builder()
            .surface(entry.getSurface())
            .leftId(entry.getLeftId())
            .rightId(entry.getRightId())
            .wordCost(entry.getWordCost())
            .pos(pos)
            .features(features)
            .build();
    }

    public List<String> extractPosFeatures(DictionaryEntry entry) {
        List<String> posFeatures = new ArrayList<>();

        posFeatures.add(entry.getPosLevel1());
        posFeatures.add(entry.getPosLevel2());
        posFeatures.add(entry.getPosLevel3());
        posFeatures.add(entry.getPosLevel4());

        posFeatures.add(entry.getConjugationType());
        posFeatures.add(entry.getConjugationForm());

        return posFeatures;
    }

    public List<String> extractOtherFeatures(DictionaryEntry entry) {
        List<String> otherFeatures = new ArrayList<>();

        otherFeatures.add(entry.getLemmaReadingForm());
        otherFeatures.add(entry.getLemma());

        otherFeatures.add(entry.getWrittenForm());
        otherFeatures.add(entry.getPronunciation());
        otherFeatures.add(entry.getWrittenBaseForm());
        otherFeatures.add(entry.getPronunciationBaseForm());

        otherFeatures.add(entry.getLanguageType());
        otherFeatures.add(entry.getInitialSoundAlterationType());
        otherFeatures.add(entry.getInitialSoundAlterationForm());
        otherFeatures.add(entry.getFinalSoundAlterationType());
        otherFeatures.add(entry.getFinalSoundAlterationForm());

        otherFeatures.add(entry.getKana());
        otherFeatures.add(entry.getKanaBase());
        otherFeatures.add(entry.getForm());
        otherFeatures.add(entry.getFormBase());
        otherFeatures.add(entry.getInitialConnectionType());
        otherFeatures.add(entry.getFinalConnectionType());

        otherFeatures.add(entry.getAccentType());
        otherFeatures.add(entry.getAccentConnectionType());
        otherFeatures.add(entry.getAccentModificationType());

        return otherFeatures;
    }
}
