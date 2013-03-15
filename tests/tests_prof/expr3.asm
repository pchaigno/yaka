; entete
extrn lirent:proc, ecrent:proc
extrn ecrbool:proc
extrn ecrch:proc, ligsuiv:proc
.model SMALL
.586

.CODE
debut :
STARTUPCODE

main :
; ouvbloc 14
enter 14, 0

; ecrireChaine "c1="
.DATA
mess0 DB "c1=$" 
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

; ecrireChaine "c3="
.DATA
mess1 DB "c3=$" 
.CODE
lea dx, mess1
push dx
call ecrch

; lireEnt -6
lea dx, [bp-6]
push dx
call lirent

; aLaLigne
call ligsuiv

; ecrireChaine "c4="
.DATA
mess2 DB "c4=$" 
.CODE
lea dx, mess2
push dx
call ecrch

; lireEnt -8
lea dx, [bp-8]
push dx
call lirent

; aLaLigne
call ligsuiv

; iload -6
push word ptr [bp-6]

; iload -8
push word ptr [bp-8]

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

; ecrireEnt
call ecrent

; aLaLigne
call ligsuiv

; iload -2
push word ptr [bp-2]

; iconst 3
push word ptr 3

; iload -2
push word ptr [bp-2]

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

; iconst 10
push word ptr 10

; isub
pop bx
pop ax
sub ax, bx
push ax

; ecrireEnt
call ecrent

; queue
nop
EXITCODE
end debut