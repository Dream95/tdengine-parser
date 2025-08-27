-- duplicate colNameList
INSERT INTO st1s1 (ts, c1, c2) USING st1 (tag1, tag2) TAGS(1, 'wxy') (ts, c1, c2) VALUES (now, 1, 'beijing');