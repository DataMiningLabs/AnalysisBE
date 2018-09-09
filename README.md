1. DESCRIPTION
--------------

The SMS Spam Corpus v.0.1 (hereafter the corpus) is a set of SMS tagged messages that have been collected for SMS Spam research. It contains two collections of SMS messages in English of 1084 and 1319 messages, tagged acording being legitimate (ham) or spam. 

1.1. Compilation
----------------

This corpus has been collected from free or free for research sources at the Web:

- A list of 202 legitimate messages, probably collected by Jon Stevenson, according to the HTML code of the Webpage. Only the text of the messages is available. We will call this corpus the Jon Stevenson Corpus (JSC). It is available at: http://www.demo.inty.net/Units/SMS/corpus.htm
- A subset of the NUS SMS Corpus (NSC), which is a corpus of about 10,000 legitimate messages collected for research at the Department of Computer Science at the National University of Singapore. The messages largely originate from Singaporeans and mostly from students attending the University. These messages were collected from volunteers who were made aware that their contributions were going to be made publicly available. The NUS SMS Corpus is avalaible at: http://www.comp.nus.edu.sg/~rpnlpir/downloads/corpora/smsCorpus/
- A collection of between 82 and 322 SMS spam messages extracted manually from the Grumbletext Web site. This is a UK forum in which cell phone users make public claims about SMS spam messages, most of them without reporting the very spam message received. The identification of the text of spam messages in the claims is a very hard and time-consuming task, and it involved carefully scanning hundreds of web pages. The Grumbletext Web site is: http://www.grumbletext.co.uk/

1.2. Statistics
---------------

There are two collections:

- The SMS Spam Corpus v.0.1 Small (file: english.txt) contains a total of 1002 legitimate messages and a total of 82 spam messages.
- The SMS Spam Corpus v.0.1 Big (file: english_big.txt) contains a total of 1002 legitimate messages and a total of 322 spam messages.

As reported at [3] in the about section, the big corpus average number of words per message is 15.72, and the average length of a word is 4.44 characters long.

1.3. Format
-----------

The files contain one message per line in raw text. Each line is finished with a coma-separated tag, which can be "ham" or "spam". Here are some examples:

Urgent! call 09061749602 from Landline. Your complimentary 4* Tenerife Holiday or Ј10,000 cash await collection SAE T&Cs BOX 528 HP20 1YF 150ppm 18+,spam

Ok then i come n pick u at engin?,ham

Anything lor... U decide...,ham

Messages are not chronologically sorted.

2. SOLUTION
--------

This app is represents solution of problem described above. This is a BE part of application, that sorts spam and ham messages and gets the most frequently rendered words.
