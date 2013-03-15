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

; ouvbloc 6
enter 6, 0

; lireEnt -2
lea dx, [bp-2]
push dx
call lirent

; aLaLigne
call ligsuiv

; lireEnt -4
lea dx, [bp-4]
push dx
call lirent

; aLaLigne
call ligsuiv

; iload -4
push word ptr [bp-4]

; istore -6
pop ax
mov word ptr [bp-6], ax

; iload -2
push word ptr [bp-2]

; iload -4
push word ptr [bp-4]

; isup
pop bx
pop ax
cmp ax, bx
jle $+6
push -1
jmp $+4
push 0

; iffaux SINON1
pop ax
cmp ax, 0
je SINON1

; iload -2
push word ptr [bp-2]

; istore -6
pop ax
mov word ptr [bp-6], ax

; goto FSI1
jmp FSI1

SINON1 :

FSI1 :

; iload -6
push word ptr [bp-6]

; ecrireEnt
call ecrent

; queue
nop
EXITCODE
end debut