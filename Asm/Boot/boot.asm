;Boot Loader  - Beta 0.0
;Func:
;    Print a string into the center of screen.

;���ó���ʼ��ַ->0x7c00��ԭʼ��ַΪ0x0000��
org 0x7c00

	;����10h�ж�����Ļ����ַ���
	mov ax,cs
	mov es,ax
	;es:bp->&str
	mov bp,msgstr
	
	;�ַ�������
	mov cx,26
	;dx->�к� �к�
	mov dh,0
	mov dl,0
	;��ʾ��ҳ��
	mov bh,0
	;��ʾ�Ĵ��ṹ
	mov al,1
	;�ַ�����
	mov bl,07h
	msgstr: db "Welcome to my Urinx OS 1.0"
	int 10h
	
	;�ظ������ֵ0
	times 510-($-$$) db 0
	;������־��������ֽڣ�Ϊ55AAa
	dw 0xaa55
	;����ѭ����ǰָ��
	jmp $