[BITS 16]
org 0x7c00
jmp main

gdt_table_start:
	;Intel�涨��������ĵ�һ������������Ϊ��0
	gdt_null:
		dd 0h
		dd 0h
	gdt_data_addr equ $-gdt_table_start
	gdt_data:
		dw 07ffh	;�ν���
		dw 0h		;�λ���ַ0-18λ
		db 0h		;�λ���ַ19-23λ
		db 10010010b	;���������ĵ������ֽ����ԣ����ݶοɶ���д��
		db 11000000b	;���������ĵ��߸��ֽ�����
		db 0		;�������������һ���ֽڣ�Ҳ���Ƕλ���ַ�ĵڶ�����
	gdt_video_addr equ $-gdt_table_start
	gdt_video:
		dw 0ffh		;�Դ�ν���1M
		dw 8000h
		db 0bh
		db 10010010b
		db 11000000b
		db 0
	gdt_code_addr equ $-gdt_table_start
	gdt_code:
		dw 07ffh	;�ν���
		dw 1h		;�λ���ַ0-18λ
		db 80h		;�λ���ַ19-23λ
		db 10011010b	;���������ĵ������ֽ����ԣ�����οɶ���ִ�У�
		db 11000000b	;���������ĵ��߸��ֽ�����
		db 0		;�λ���ַ�ĵڶ�����
gdt_table_end:

gdtr_addr:
	dw gdt_table_end-gdt_table_start-1	;����������
	dd gdt_table_start			;�����������ַ

main:
	xor eax,eax				;��ʼ�����ݶ��������Ļ���ַ
	add eax,data_32
	mov word [gdt_data+2],ax
	shr eax,16
	mov byte [gdt_data+4],al
	mov byte [gdt_data+7],ah

	xor eax,eax				;��ʼ��������������Ļ���ַ
	add eax,code_32
	mov word [gdt_code+2],ax
	shr eax,16
	mov byte [gdt_code+4],al
	mov byte [gdt_code+7],ah

	cli
	lgdt [gdtr_addr]			;��cpu��ȡgdtr_addr��ָ���ڴ�����

	enable_A20:				;A20��ַ�߿���
		in al,92h
		or al,00000010b
		out 92h,al
		

	enter_pmode:				;���뱣��ģʽ
		mov eax,cr0
		or eax,1
		mov cr0,eax
		;��ת������ģʽ��
		jmp gdt_code_addr:0

[BITS 32]
	;�ڱ���ģʽ�д�ӡ�ַ�
	data_32:
		msg db 'hello world'

		len equ $-msg
	code_32:
		mov ax,gdt_data_addr
		mov ds,ax
		mov ax,gdt_video_addr
		mov es,ax
		mov cx,len
		mov edi,0
		mov bx,0
		mov ah,0ch
	s:	mov al,[ds:bx]
		mov [es:edi],al
		mov [es:edi+1],ah
		inc bx
		add edi,2
		loop s

		jmp $
		times 510-($-$$) db 0
		dw 0xaa55