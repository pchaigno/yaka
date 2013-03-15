; entete
extrn lirent:proc, ecrent:proc
extrn ecrbool:proc
extrn ecrch:proc, ligsuiv:proc
.model SMALL
.586

.CODE

max :

; ouvbloc 6
enter 6, 0

; iload 6
push word ptr [bp+6]

; istore -6
pop ax
mov word ptr [bp-6], ax

; iload -6
push word ptr [bp-6]

; iload 4
push word ptr [bp+4]

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

; ireturn 8
pop ax
mov [bp+8], ax

; goto FSI1
jmp FSI1

SINON1 :

; iload 4
push word ptr [bp+4]

; ireturn 8
pop ax
mov [bp+8], ax

FSI1 :

; fermebloc 4
leave
ret 4

debut :
STARTUPCODE

main :

; ouvbloc 8
enter 8, 0

; iconst 5
push word ptr 5

; istore -2
pop ax
mov word ptr [bp-2], ax

; lireEnt -4
lea dx, [bp-4]
push dx
call lirent

; aLaLigne
call ligsuiv

; reserveRetour
sub sp, 2

; iload -2
push word ptr [bp-2]

; iload -4
push word ptr [bp-4]

; call max
call max

; iconst 2
push word ptr 2

; iadd
pop bx
pop ax
add ax, bx
push ax

; istore -6
pop ax
mov word ptr [bp-6], ax

; aLaLigne
call ligsuiv

; iload -6
push word ptr [bp-6]

; ecrireEnt
call ecrent

; queue
nop
EXITCODE
end debut
