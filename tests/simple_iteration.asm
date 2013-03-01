; entete
extrn lirent:proc, ecrent:proc
extrn ecrbool:proc
extrn ecrch:proc, ligsuiv:proc
.model SMALL
.586

.CODE
debut :
STARTUPCODE

; ouvrePrinc 6
mov bp, sp
sub sp, 6

; iconst 5
push word ptr 5

; istore -6
pop ax
mov word ptr [bp-6], ax

; iconst 1
push word ptr 1

; istore -4
pop ax
mov word ptr [bp-4], ax

; iconst 0
push word ptr 0

; istore -2
pop ax
mov word ptr [bp-2], ax

FAIRE1 :

; iload -4
push word ptr [bp-4]

; iload -6
push word ptr [bp-6]

; iinfegal
pop bx
pop ax
cmp ax, bx
jg $+6
push -1
jmp $+4
push 0

; iffaux FAIT1
pop ax
cmp ax, 0
je FAIT1

; iload -2
push word ptr [bp-2]

; iload -4
push word ptr [bp-4]

; iadd
pop bx
pop ax
add ax, bx
push ax

; istore -2
pop ax
mov word ptr [bp-2], ax

; iload -4
push word ptr [bp-4]

; iconst 1
push word ptr 1

; iadd
pop bx
pop ax
add ax, bx
push ax

; istore -4
pop ax
mov word ptr [bp-4], ax

; goto FAIRE1
jmp FAIRE1

FAIT1 :

; queue
nop
EXITCODE
end debut