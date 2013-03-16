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

; iconst 5
push word ptr 5

; istore -2
pop ax
mov word ptr [bp-2], ax

; iconst 10
push word ptr 10

; istore -4
pop ax
mov word ptr [bp-4], ax

; iconst 1
push word ptr 1

; ineg
pop ax
neg ax
push ax

; istore -6
pop ax
mov word ptr [bp-6], ax

; iload -2
push word ptr [bp-2]

; iload -6
push word ptr [bp-6]

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

; iload -6
push word ptr [bp-6]

; iconst 10
push word ptr 10

; isup
pop bx
pop ax
cmp ax, bx
jle $+6
push -1
jmp $+4
push 0

; iffaux SINON2
pop ax
cmp ax, 0
je SINON2

; iconst 0
push word ptr 0

; istore -2
pop ax
mov word ptr [bp-2], ax

; goto FSI2
jmp FSI2

SINON2 :

; iconst 10
push word ptr 10

; istore -2
pop ax
mov word ptr [bp-2], ax

FSI2 :

; iload -6
push word ptr [bp-6]

; iload -6
push word ptr [bp-6]

; imul
pop bx
pop ax
imul bx
push ax

; iload -2
push word ptr [bp-2]

; imul
pop bx
pop ax
imul bx
push ax

; iconst 10
push word ptr 10

; iinf
pop bx
pop ax
cmp ax, bx
jge $+6
push -1
jmp $+4
push 0

; iffaux SINON3
pop ax
cmp ax, 0
je SINON3

; iconst 42
push word ptr 42

; istore -6
pop ax
mov word ptr [bp-6], ax

; goto FSI3
jmp FSI3

SINON3 :

FSI3 :

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
