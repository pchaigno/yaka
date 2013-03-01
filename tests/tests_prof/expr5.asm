; entete
extrn lirent:proc, ecrent:proc
extrn ecrbool:proc
extrn ecrch:proc, ligsuiv:proc
.model SMALL
.586

.CODE
debut :
STARTUPCODE

; ouvrePrinc 4
mov bp, sp
sub sp, 4

; iconst 8
push word ptr 8

; istore -2
pop ax
mov word ptr [bp-2], ax

; ecrireChaine "c1="
.DATA
mess0 DB "c1=$" 
.CODE
lea dx, mess0
push dx
call ecrch

; iload -2
push word ptr [bp-2]

; ecrireEnt
call ecrent

; aLaLigne
call ligsuiv

; ecrireChaine "-45="
.DATA
mess1 DB "-45=$" 
.CODE
lea dx, mess1
push dx
call ecrch

; iconst 2
push word ptr 2

; ineg
pop ax
neg ax
push ax

; iload -2
push word ptr [bp-2]

; imul
pop bx
pop ax
imul bx
push ax

; iconst 3
push word ptr 3

; ineg
pop ax
neg ax
push ax

; iconst 4
push word ptr 4

; imul
pop bx
pop ax
imul bx
push ax

; iconst 2
push word ptr 2

; imul
pop bx
pop ax
imul bx
push ax

; iadd
pop bx
pop ax
add ax, bx
push ax

; iconst 5
push word ptr 5

; isub
pop bx
pop ax
sub ax, bx
push ax

; ecrireEnt
call ecrent

; aLaLigne
call ligsuiv

; queue
nop
EXITCODE
end debut