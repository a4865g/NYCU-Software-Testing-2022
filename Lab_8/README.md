# Lab 8: Symbolic Execution
1. 我們提供一個 linux binary
	- 請透過 angr 產生出他的 cfg
	- 請找到能夠讓該程式印出 correct\n 的輸入
2. 繳交方式 : 學號.zip (zip內包含以下檔案)
	- target.cfg : 用來找位置的 cfg 圖片
	- solve.py : 執行 angr 的 python (please use python3)
	- flag.txt : 正確的參數輸入 (argv[1])