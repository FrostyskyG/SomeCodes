assume cs:code

code segment
start:
	;1.�޸��ж�������
	;�ж�������:0000:0000->0000:03FE,ÿ������2��(4�ֽ�,ƫ��+��ַ)
	;�޸�����жϱ��ָ���Զ����жϴ���������ڵ�ַ
	;û����������ʹ�õ��ڴ�:0000:0200->0000:0300
	mov ax,0
	mov ds,ax
	mov word ptr ds:[0],200h
	mov word ptr ds:[2],0
	
	;3.���Զ����жϳ����Ƶ��ڴ�0000:0200
	;��ʱcsΪ��ǰ����ε�ַ
	mov ax,cs
	mov ds,ax
	mov si,offset int0
	mov ax,0
	mov es,ax
	mov di,200h
	;����ָ����ַ���ַ���
	;���ó���
	mov cx,offset int0end-offset int0
	;ָ�����Ʒ�������
	cld
	;ds:si->es:di
	rep movsb
	
	;4.��������ж�
	mov ax,1000h
	mov bl,1
	div bl
	
	;2.�Զ�������ж�
int0:
	jmp short int0start ; ��ַ:0200h
	db "What the fuck!" ; ��ַ:0202h
int0start:
	;�����Դ�
	mov ax,0b800h
	mov es,ax
	;��ʾ�ַ�
	;��ʱcs:0000h
	mov ax,cs
	mov ds,ax
	;si->�ַ���
	mov si,202h
	;80x24��Ļ�м�ƫ��
	mov di,12*80*2+32*2
	mov cx,14
s:
	mov al,ds:[si]
	mov es:[di],al
	inc si
	add di,2
	loop s
	
	;PressAnyKey�ж�
	mov ax,4c00h
	int 21h
int0end:nop

code ends
	end start
