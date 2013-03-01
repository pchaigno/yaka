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

; ecrireChaine "x="
.DATA
mess0 DB "x=$" 
.CODE
lea dx, mess0
push dx
call ecrch

; lireEnt -2
lea dx, [bp-2]
push dx
call lirent

; aLaLigne
call ligsuiv

; ecrireChaine "y="
.DATA
mess1 DB "y=$" 
.CODE
lea dx, mess1
push dx
call ecrch

; lireEnt -4
lea dx, [bp-4]
push dx
call lirent

; aLaLigne
call ligsuiv

; ecrireChaine "x+y="
.DATA
mess2 DB "x+y=$" 
.CODE
lea dx, mess2
push dx
call ecrch

; iload -2
push word ptr [bp-2]

; iload -4
push word ptr [bp-4]

; iadd
pop bx
pop ax
add ax, bx
push ax

; ecrireEnt
call ecrent

; aLaLigne
call ligsuiv

; iload -2
push word ptr [bp-2]

; iload -4
push word ptr [bp-4]

; iconst 2
push word ptr 2

; idiv
pop bx
pop ax
cwd
idiv bx
push ax

; iadd
pop bx
pop ax
add ax, bx
push ax

; iconst 5
push word ptr 5

; idiv
pop bx
pop ax
cwd
idiv bx
push ax

; istore -6
pop ax
mov word ptr [bp-6], ax

; iload -4
push word ptr [bp-4]

; iconst 3
push word ptr 3

; iload -4
push word ptr [bp-4]

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

; iconst 4
push word ptr 4

; isub
pop bx
pop ax
sub ax, bx
push ax

; istore -2
pop ax
mov word ptr [bp-2], ax

; queue
nop
EXITCODE
end debut