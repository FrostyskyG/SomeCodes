assume cs:code,ds:data

data segment
	msg db "Hello world"
data ends

code segment
start:
	;���ݶε�ַ->ds
	mov ax,data
	mov ds,ax
	;�Դ��ַ->es
	mov bx,0b800h
	mov es,bx
	;���ݶ�ƫ��si,���Ӷ�ƫ��bx
	mov si,0
	mov bx,0
	;�ն��ַ�RGB��ʾģʽ
	mov ah,00000111b
	;�ַ�������
	mov cx,11
  s:mov al,ds:[si]
	mov es:[bx],ax
	inc si
	add bx,2
	loop s
	;PressAnyKey DOS�ж�
	mov ax,4c00h
	int 21h
code ends
	end start
