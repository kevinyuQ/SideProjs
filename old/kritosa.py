#from math import random
from nltk.tokenize import word_tokenize

alphabet = 'abcdefghijklmnopqrstuvwxyz'
vowels = 'aeiou'
consonants = [char for char in alphabet if char not in vowels]

def simple_encrypt(message):
    """
    Takes an English message and then returns the encrypted message using the
    following encryption: shift consonants over one spot in the consonants list,
    and shift vowels over one spot.

    >>> 'The cake is a lie.'
    'Vji deli ot e moi.'
    """
    key = {}
    encryption = ''
    for char in alphabet:
        if char in consonants:
            index = consonants.index(char)
            key[char] = consonants[(index + 1) % len(consonants)]
        elif char in vowels:
            index = vowels.index(char)
            key[char] = vowels[(index + 1) % len(vowels)]
    for char in message:
        if char not in key:
            encryption += char
        else:
            encryption += key[char]
    return encryption

def count_syllables(message):
    """
    Counts the number of syllables in a word based on how many monophthongs and
    diphthongs there are in the word.

    >>> count_syllables('all human beings are born free and equal')
    10
    """


def phonetic_transcribe(message):
    """
    Returns list containing the characters and digraphs of the phonetic spelling
    of message, using my bullshit phonetic spelling of things. See <*insert document
    name here or something> for a full list of which letters and digraphs correspond
    to which phonemes. Schwa and wedge will be treated as the same.

    >>> phonetic_transcribe('I am a werewolf')
    'ai aem ^ w3rwolf'

    >>> phonetic_transcribe('Gaia has arrived')
    'gaie haez eraivd'

    >>> phonetic_transcribe('The wolf is a puppy')
    'dhe wolf Iz ^ p^ppi'

    >>> phonetic_transcribe('the quick, brown fox jumped')
    """

### WORK ON THIS FUNCTION LATER
def lingwa_inkripto(message):
    """
    Based on an old cipher that I tried using

    ...
    ...
    ...
    """
    count = 0
    for char in message:
        if char in consonants:
            count += 1
        if char in vowels:
            count += 1
    return result
